import greenfoot.*;

public class Ball extends Actor
{
    private final int   BALL_SIZE = 25;
    private final int   BOUNCE_DEVIANCE_MAX = 5;
    private final int   STARTING_ANGLE_WIDTH = 90;
    private final int   DELAY_TIME = 100;
    private final int   HITS_FOR_SPEED = 10;
    private final int   HIGHSCORE_SETBACK = 10;
    private final int   MAX_ANGLE = 60;
    private final float MINIMUM_SPEED = 1f;
    private final float DRAG_COEFFICIENT = 0.995f;
    private final int   WIN_BONUS = 1000;

    private float positionX;
    private float positionY;
    private float velocity;
    private float speed;
    private int   ownHits;
    private int   delay;
    
    private Paddle bottomPaddle;
    private Paddle topPaddle;
    private PingWorld pingWorld;
    
    
    private Product skin = Shop.products[GameManager.getSkin()];
    
    private GreenfootImage image = skin.images.getBall();
    
    public Ball(Paddle bottomPaddle, Paddle topPaddle) {
        this.bottomPaddle = bottomPaddle;
        this.topPaddle = topPaddle;
        
        createImage();
    }
    
    public void addedToWorld(World world) { resetBall(); }
    
    public int getSize() { return BALL_SIZE; }
    
    private void createImage() {
        image.scale(BALL_SIZE, BALL_SIZE);
        setImage(image);
    }

    public void act() {
        if (topPaddle.getBall() == null) { topPaddle.setBall(this); }
        if (bottomPaddle.getBall() == null) { bottomPaddle.setBall(this); }
        
        if (delay > 0) { delay--; return; }
        
        positionX += Math.cos(Math.toRadians(getRotation())) * (velocity * speed + MINIMUM_SPEED);
        positionY += Math.sin(Math.toRadians(getRotation())) * (velocity * speed + MINIMUM_SPEED);
        setLocation((int) positionX, (int) positionY);
        applyDrag();
        
        bounceWalls();
        checkBounceOffPaddleBottom();
        checkBounceOffPaddleTop();
        checkRestart();
    }

    public int getHitsForSpeed() { return HITS_FOR_SPEED; }
    
    private boolean isTouchingSides() { return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2); }
    private boolean isTouchingCeiling() { return (getY() <= BALL_SIZE/2); }
    private boolean isTouchingFloor() { return (getY() >= getWorld().getHeight() - BALL_SIZE/2); }
    
    private void bounceWalls() {
        if (!isTouchingSides()) { return; }
        
        if (90 < getRotation() && getRotation() < 270
            && getX() < getWorld().getWidth() / 2)
            { setRotation(180 - getRotation()); }
            
        if ((
            0 < getRotation() && getRotation() < 90
            || 270 < getRotation()
            && getRotation() < 360
            )
            && getX() > getWorld().getWidth() / 2)
            { setRotation(180 - getRotation()); }
        
        
        SoundManager.playWallHit();
    }
    
    private void checkRestart() {
        if (!isTouchingFloor() && !isTouchingCeiling()) { return; }
        if (isTouchingCeiling() && !bottomPaddle.isAI()) { applyWin(); }
        if (isTouchingFloor() && !bottomPaddle.isAI()) { applyLoss(); }
        
        resetBall();
    }
    
    private void applyWin() {
        SoundManager.playWin();
        addMoney();
        GameManager.setWins(GameManager.getWins() + 1);
        GameManager.setMoney(GameManager.getMoney() + WIN_BONUS);
        SaveManager.saveData();
    }
    
    private void applyLoss() {
        SoundManager.playLoss();
        addMoney();
        GameManager.setLoses(GameManager.getLoses() + 1);
        SaveManager.saveData();
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
        if (   getIntersectingObjects(Paddle.class).size() < 1
            || getIntersectingObjects(Paddle.class).get(0).getY() > getWorld().getWidth() / 2
            || getRotation() < 180)
            { return; }
        
        hitPaddle();
        
        velocity = 1;
        
        turnAwayFrom(topPaddle.getX(), topPaddle.getY(), true);
        
        if ( topPaddle.isAI() ) { topPaddle.resetToCenter(); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.setupPredictor(this); }
    }
    
    private void checkBounceOffPaddleBottom() {
        if (getIntersectingObjects(Paddle.class).size() < 1
            || getIntersectingObjects(Paddle.class).get(0).getY() < getWorld().getWidth() / 2
            || getRotation() > 180)
            { return; }
        
        ownHits += 1;
        hitPaddle();
        
        velocity = 1;
    
        turnAwayFrom(bottomPaddle.getX(), bottomPaddle.getY(), false);
        
        if ( topPaddle.isAI() ) { topPaddle.setupPredictor(this); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.resetToCenter(); }
        
        if ( bottomPaddle.isAI() ) { return; }
        pingWorld.scoreText(ownHits);
            
        if (ownHits <= GameManager.getHighscore() || GameManager.getHighscore() < 0 ) { return; }
        GameManager.setHighscore(ownHits);
        pingWorld.highscoreText(GameManager.getHighscore());
    }
    
    private void hitPaddle() {
        SoundManager.playPaddleHit();
        increaseSpeed();
    }
    
    private void increaseSpeed() {
        speed = (float) ownHits / (float) HITS_FOR_SPEED;
        speed++;
        
        pingWorld = (PingWorld) getWorld();
        pingWorld.speedText(speed);
    }
    
    private void applyDrag() { velocity *= DRAG_COEFFICIENT; }
    
    private void turnAwayFrom(int x, int y, boolean goingUp) {
        turnTowards(x, y);
        if (goingUp) { setRotation(Math.clamp((180 + getRotation()) % 360, 90 - MAX_ANGLE, 90 + MAX_ANGLE)); }
        else { setRotation(Math.clamp((180 + getRotation()) % 360, 270 - MAX_ANGLE, 270 + MAX_ANGLE)); }
    }
    
    private void resetBall() {
        int x = getWorld().getWidth() / 2;
        int y = getWorld().getHeight() - 60 - BALL_SIZE;
        setLocation(x, y);
        
        positionX = getX();
        positionY = getY();
        
        velocity = 1;
        
        ownHits = GameManager.getHighscore() - HIGHSCORE_SETBACK;
        ownHits = (int) Math.clamp(ownHits, 0, Double.POSITIVE_INFINITY);
        
        speed = (float) ownHits / (float) HITS_FOR_SPEED;
        speed++;
        
        delay = DELAY_TIME;
        
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2 + 180);
        
        topPaddle.setupPredictor(this);
        if ( bottomPaddle.isAI() ) { bottomPaddle.resetToCenter(); }
        
        if (pingWorld == null) { return; }
        pingWorld.speedText(speed);
        pingWorld.highscoreText(GameManager.getHighscore());
        pingWorld.moneyText(GameManager.getMoney());
        pingWorld.scoreText(ownHits);
    }
}