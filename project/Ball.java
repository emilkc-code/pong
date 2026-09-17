import greenfoot.*;

public class Ball extends Actor
{
    /* Instance variables and default/essential "settings" */
    private final int BALL_SIZE = 25;
    private final int BOUNCE_DEVIANCE_MAX = 5;
    private final int STARTING_ANGLE_WIDTH = 90;
    private final int DELAY_TIME = 100;
    private final int HITS_FOR_SPEED = 10;
    private final int MAX_ANGLE = 60;
    private final float MINIMUM_SPEED = 2f;
    private final float DRAG_COEFFICIENT = 0.995f;
    private final float SPEED_INCREASE = 0.5f;

    /* Points and info for calculation */
    private float positionX;
    private float positionY;
    private float velocity;
    private float speed;
    private int ownHits;
    private int delay;
    private GreenfootImage image;
    
    /* Paddles, pingWorld and gameManager */
    private Paddle bottomPaddle;
    private Paddle topPaddle;
    private PingWorld pingWorld;
    private GameManager gameManager = new GameManager();
    private SaveManager saveManager = new SaveManager();

    /* Sounds */
    private GreenfootSound wallHitSound;
    private GreenfootSound paddleHitSound;
    
    /* Constructor */
    public Ball(Paddle bottomPaddle, Paddle topPaddle, GreenfootImage img, GreenfootSound wallHitS, GreenfootSound paddleHitS) {
        this.bottomPaddle = bottomPaddle;
        this.topPaddle = topPaddle;
        this.image = img;
        this.wallHitSound = wallHitS;
        this.paddleHitSound = paddleHitS;
        createImage();
    }
    
    /* Initialiser call method */
    public void addedToWorld(World world) { init(true); }

    /**
     * Get ball size
     */
    public int getSize() { return BALL_SIZE; }
    
    /**
     * Scale and set ball img
     */
    private void createImage() {
        image.scale(BALL_SIZE, BALL_SIZE);
        setImage(image);
    }

    public void act() {
        if (topPaddle.getBall() == null) { topPaddle.setBall(this); }
        if (bottomPaddle.getBall() == null) { bottomPaddle.setBall(this); }
        
        if (delay > 0) { delay--; }

        /* Logic behind the ball's movement (Vector based math) */
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

    /* Methods for is Touching sides, ceiling and or floor. */
    private boolean isTouchingSides() { return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2); }
    private boolean isTouchingCeiling() { return (getY() <= BALL_SIZE/2); }
    private boolean isTouchingFloor() { return (getY() >= getWorld().getHeight() - BALL_SIZE/2); }

    /**
     * Bounce off walls method
     */
    private void bounceWalls() {
        if (!isTouchingSides()) { return; }
        
        if (90 < getRotation() && getRotation() < 270 && getX() < getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        if ((0 < getRotation() && getRotation() < 90 || 270 < getRotation() && getRotation() < 360) && getX() > getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        
        
        wallHitSound.play();
    }
    
    /**
     * CheckRestart method for when said player passes the ball past "enemy" paddle.
     */
    private void checkRestart() {
        if (!isTouchingFloor() && !isTouchingCeiling()) { return; }
        if (isTouchingCeiling() && !bottomPaddle.isAI()) {
            addMoney();
            gameManager.setWins(gameManager.getWins() + 1);
            gameManager.setMoney(gameManager.getMoney() + 100000);
            gameManager.setHighscore(-1);
            saveManager.saveHighScore(-1);
        }
        if (isTouchingFloor() && !bottomPaddle.isAI()) {
            addMoney();
            gameManager.setLoses(gameManager.getLoses() + 1);
            saveManager.saveHighScore(gameManager.getHighscore());
        }
        
        init(true);
    }

    /**
     * Adding money in gamemanager
     */
    private void addMoney() {
        gameManager.setMoney(gameManager.getMoney() + 1 * ownHits * ((int) (ownHits / 10) + 1));
    }
    
    /**
     * Checking for bounce off of top paddle. (Look towards paddle center and revert på 180 degrees
     */
    private void checkBounceOffPaddleTop() {
        if (getIntersectingObjects(Paddle.class).size() < 1 || getIntersectingObjects(Paddle.class).get(0).getY() > getWorld().getWidth() / 2 || getRotation() < 180) { return; }
        hitPaddle();
        
        velocity = 1;
        
        turnAwayFrom(topPaddle.getX(), topPaddle.getY(), true);
        
        if ( topPaddle.isAI() ) { topPaddle.resetToCenter(); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.setupPredictor(this); }
    }
    
    /**
     * Checking for bounce off of bottom paddle (Same logic as above)
     */
    private void checkBounceOffPaddleBottom() {
        if (getIntersectingObjects(Paddle.class).size() < 1 || getIntersectingObjects(Paddle.class).get(0).getY() < getWorld().getWidth() / 2 || getRotation() > 180) { return; }
        ownHits += 1;
        hitPaddle();
        
        if ( !bottomPaddle.isAI() ) {
            pingWorld.scoreText(ownHits);
            
            if (ownHits > gameManager.getHighscore() && gameManager.getHighscore() >= 0 ) {
                gameManager.setHighscore(ownHits);
                pingWorld.highscoreText(gameManager.getHighscore());
            }
        }
        
        velocity = 1;
    
        turnAwayFrom(bottomPaddle.getX(), bottomPaddle.getY(), false);
        
        if ( topPaddle.isAI() ) { topPaddle.setupPredictor(this); }
        if ( bottomPaddle.isAI() ) { bottomPaddle.resetToCenter(); }
    }
    
    /**
     * Plays sound for hitting paddle
     */
    private void hitPaddle() {
        paddleHitSound.play();
        increaseSpeed();
    }
    
    /**
     * Increases speed after a set number of collisions
     */
    private void increaseSpeed() {
        pingWorld = (PingWorld) getWorld();
        if (ownHits % HITS_FOR_SPEED == 0) {
            speed += SPEED_INCREASE;
            pingWorld.levelText((int) (ownHits / HITS_FOR_SPEED + 1));
        }
    }
    
    /**
     * Applies drag (for ball movement logic)
     */
    private void applyDrag() {
        velocity *= DRAG_COEFFICIENT;
    }
    
    /**
     * Rotation flipper.
     */
    private void turnAwayFrom(int x, int y, boolean goingUp) {
        turnTowards(x, y);
        if (goingUp) { setRotation(Math.clamp((180 + getRotation()) % 360, 90 - MAX_ANGLE, 90 + MAX_ANGLE)); }
        else { setRotation(Math.clamp((180 + getRotation()) % 360, 270 - MAX_ANGLE, 270 + MAX_ANGLE)); }
    }
    
    /**
     * Ball reseter
     */
    private void init(boolean reset) {
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() - 60 - BALL_SIZE);
        
        positionX = getX();
        positionY = getY();
        
        velocity = 1;
        speed = 0;
        delay = DELAY_TIME;
        ownHits = 0;
        
        if (pingWorld != null) {
            pingWorld.levelText(1);
            pingWorld.highscoreText(gameManager.getHighscore());
            pingWorld.moneyText(gameManager.getMoney());
            pingWorld.scoreText(ownHits);
        }
        
        if (reset == false) { return; }
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2 + 180);
        
        topPaddle.setupPredictor(this);
    }
}