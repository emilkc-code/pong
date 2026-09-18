import greenfoot.*;

public class Ball extends Actor
{
    private final int BALL_SIZE = 25;
    private final int BOUNCE_DEVIANCE_MAX = 5;
    private final int STARTING_ANGLE_WIDTH = 90;
    private final int DELAY_TIME = 100;
    private final int HITS_FOR_SPEED = 10;
    private final int HIGHSCORE_SETBACK = 10;
    private final int MAX_ANGLE = 60;
    private final float MINIMUM_SPEED = 1f;
    private final float DRAG_COEFFICIENT = 0.995f;

    private float positionX;
    private float positionY;
    private float velocity;
    private float speed;
    private int ownHits;
    private int delay;
    private GreenfootImage image;
    
    private Paddle bottomPaddle;
    private Paddle topPaddle;
    private PingWorld pingWorld;
    
    private GreenfootSound wallHitSound;
    private GreenfootSound paddleHitSound;
    
    public Ball(Paddle bottomPaddle, Paddle topPaddle, GreenfootImage img, GreenfootSound wallHitS, GreenfootSound paddleHitS) {
        this.bottomPaddle = bottomPaddle;
        this.topPaddle = topPaddle;
        this.image = img;
        this.wallHitSound = wallHitS;
        this.paddleHitSound = paddleHitS;
        createImage();
    }
    
    public void addedToWorld(World world) { init(true); }
    
    public int getSize() { return BALL_SIZE; }
    
    private void createImage() {
        image.scale(BALL_SIZE, BALL_SIZE);
        setImage(image);
    }

    public void act() {
        if (topPaddle.getBall() == null) { topPaddle.setBall(this); }
        if (bottomPaddle.getBall() == null) { bottomPaddle.setBall(this); }
        
        if (delay > 0) { delay--; }
        
        else {
            positionX += Math.cos(Math.toRadians(getRotation())) * (velocity * speed + MINIMUM_SPEED);
            positionY += Math.sin(Math.toRadians(getRotation())) * (velocity * speed + MINIMUM_SPEED);
            setLocation((int) positionX, (int) positionY);
            applyDrag();
            
            bounceWalls();
            checkBounceOffPaddleBottom();
            checkBounceOffPaddleTop();
            checkRestart();
        }
    }

    public int getHitsForSpeed() { return HITS_FOR_SPEED; }
    
    private boolean isTouchingSides() { return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2); }
    private boolean isTouchingCeiling() { return (getY() <= BALL_SIZE/2); }
    private boolean isTouchingFloor() { return (getY() >= getWorld().getHeight() - BALL_SIZE/2); }
    
    private void bounceWalls() {
        if (!isTouchingSides()) { return; }
        
        if (90 < getRotation() && getRotation() < 270 && getX() < getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        if ((0 < getRotation() && getRotation() < 90 || 270 < getRotation() && getRotation() < 360) && getX() > getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        
        
        wallHitSound.stop();
        wallHitSound.play();
    }
    
    private void checkRestart() {
        if (!isTouchingFloor() && !isTouchingCeiling()) { return; }
        if (isTouchingCeiling() && !bottomPaddle.isAI()) {
            addMoney();
            GameManager.setWins(GameManager.getWins() + 1);
            GameManager.setMoney(GameManager.getMoney() + 100000);
            GameManager.setHighscore(-1);
            SaveManager.saveHighScore(-1);
        }
        if (isTouchingFloor() && !bottomPaddle.isAI()) {
            addMoney();
            GameManager.setLoses(GameManager.getLoses() + 1);
            SaveManager.saveHighScore(GameManager.getHighscore());
        }
        
        init(true);
    }
    
    private void addMoney() {
        float money = 1 * (float) ownHits * ((float) ownHits / (float) HITS_FOR_SPEED + 1);
        float highscoreMultiplier = 1.0f;
        if (GameManager.getHighscore() >= HIGHSCORE_SETBACK) {
            highscoreMultiplier = 1.0f - (float) (GameManager.getHighscore() - ownHits) / (float) HIGHSCORE_SETBACK;
        }
        money *= Math.pow(highscoreMultiplier, 4);
        GameManager.setMoney((int) (GameManager.getMoney() + money));
    }
    
    private void checkBounceOffPaddleTop() {
        if (getIntersectingObjects(Paddle.class).size() < 1 || getIntersectingObjects(Paddle.class).get(0).getY() > getWorld().getWidth() / 2 || getRotation() < 180) { return; }
        hitPaddle();
        
        velocity = 1;
        
        turnAwayFrom(topPaddle.getX(), topPaddle.getY(), true);
        
        if ( topPaddle.isAI() ) { topPaddle.resetToCenter(); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.setupPredictor(this); }
    }
    
    private void checkBounceOffPaddleBottom() {
        if (getIntersectingObjects(Paddle.class).size() < 1 || getIntersectingObjects(Paddle.class).get(0).getY() < getWorld().getWidth() / 2 || getRotation() > 180) { return; }
        ownHits += 1;
        hitPaddle();
        
        if ( !bottomPaddle.isAI() ) {
            pingWorld.scoreText(ownHits);
            
            if (ownHits > GameManager.getHighscore() && GameManager.getHighscore() >= 0 ) {
                GameManager.setHighscore(ownHits);
                pingWorld.highscoreText(GameManager.getHighscore());
            }
        }
        
        velocity = 1;
    
        turnAwayFrom(bottomPaddle.getX(), bottomPaddle.getY(), false);
        
        if ( topPaddle.isAI() ) { topPaddle.setupPredictor(this); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.resetToCenter(); }
    }
    
    private void hitPaddle() {
        paddleHitSound.stop();
        paddleHitSound.play();
        increaseSpeed();
    }
    
    private void increaseSpeed() {
        pingWorld = (PingWorld) getWorld();
        speed = 1 + (float) ownHits / (float) HITS_FOR_SPEED;
        pingWorld.speedText(speed);
    }
    
    private void applyDrag() {
        velocity *= DRAG_COEFFICIENT;
    }
    
    private void turnAwayFrom(int x, int y, boolean goingUp) {
        turnTowards(x, y);
        if (goingUp) { setRotation(Math.clamp((180 + getRotation()) % 360, 90 - MAX_ANGLE, 90 + MAX_ANGLE)); }
        else { setRotation(Math.clamp((180 + getRotation()) % 360, 270 - MAX_ANGLE, 270 + MAX_ANGLE)); }
    }
    
    private void init(boolean reset) {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() - 60 - BALL_SIZE);
        
        positionX = getX();
        positionY = getY();
        
        velocity = 1;
        ownHits = (int) Math.clamp(GameManager.getHighscore() - HIGHSCORE_SETBACK, 0, Double.POSITIVE_INFINITY);
        speed = 1 + (float) ownHits / (float) HITS_FOR_SPEED;
        delay = DELAY_TIME;
        
        if (pingWorld != null) {
            pingWorld.speedText(speed);
            pingWorld.highscoreText(GameManager.getHighscore());
            pingWorld.moneyText(GameManager.getMoney());
            pingWorld.scoreText(ownHits);
        }
        
        if (reset == false) { return; }
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2 + 180);
        
        topPaddle.setupPredictor(this);
        if ( bottomPaddle.isAI() ) { bottomPaddle.resetToCenter(); }
    }
}