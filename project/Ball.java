import greenfoot.*;

public class Ball extends Actor
{
    private final int BALL_SIZE = 25;
    private final int BOUNCE_DEVIANCE_MAX = 5;
    private final int STARTING_ANGLE_WIDTH = 90;
    private final int DELAY_TIME = 100;
    private final int HITS_FOR_SPEED = 4;
    private final int MAX_ANGLE = 60;

    private int speed;
    private int ownHits;
    private int delay;
    
    private Paddle bottomPaddle;
    private AIPaddle topPaddle;
    
    private PingWorld pingWorld;
    private GameManager gameManager = new GameManager();
    
    public Ball(Paddle bottomPaddle, AIPaddle topPaddle) {
        createImage();
        init(true);
        this.bottomPaddle = bottomPaddle;
        this.topPaddle = topPaddle;
    }

    private void createImage() {
        GreenfootImage img = new GreenfootImage("c4.png");
        img.scale(BALL_SIZE, BALL_SIZE);
        setImage(img);
    }

    public void act() {
        if (delay > 0) { delay--; }
        else {
            move(speed);
            bounceWalls();
            checkBounceOffPaddleBottom();
            checkBounceOffPaddleTop();
            checkRestart();
        }
    }    

    private boolean isTouchingSides() { return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2); }
    
    private boolean isTouchingCeiling() { return (getY() <= BALL_SIZE/2); }
    private boolean isTouchingFloor() { return (getY() >= getWorld().getHeight() - BALL_SIZE/2); }

    private void bounceWalls() {
        if (!isTouchingSides()) { return; }
        
        if (90 < getRotation() && getRotation() < 270 && getX() < getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        if ((0 < getRotation() && getRotation() < 90 || 270 < getRotation() && getRotation() < 360) && getX() > getWorld().getWidth() / 2) { setRotation(180 - getRotation()); }
        
        GreenfootSound onPaddleHit = new GreenfootSound("WallThudSound.mp3");
        onPaddleHit.play();
    }
    
    private void checkRestart() {
        if (!isTouchingFloor() && !isTouchingCeiling()) { return; }
        if (isTouchingCeiling()) { applyWin(); }
        if (isTouchingFloor()) { gameManager.setLoses(gameManager.getLoses() + 1); }
        
        init(true);
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }

    private void applyWin() {
        gameManager.setWins(gameManager.getWins() + 1);
        gameManager.setMoney(gameManager.getMoney() + 1 * ownHits * ((int) (ownHits / 10) + 1));
        pingWorld = (PingWorld) getWorld();
        pingWorld.moneyText(gameManager.getMoney());
    }
    
    private void checkBounceOffPaddleTop() {
        if (getIntersectingObjects(AIPaddle.class).size() < 1 || getRotation() < 180) { return; }
        
        AIPaddle paddle = (AIPaddle) getIntersectingObjects(AIPaddle.class).get(0);
        turnAwayFrom(paddle.getX(), paddle.getY(), true);
        
        GreenfootSound bombBeepSound = new GreenfootSound("BombBeep.mp3");
        bombBeepSound.play();
    }
    
    private void checkBounceOffPaddleBottom() {
        if (getIntersectingObjects(Paddle.class).size() < 1 || getRotation() > 180) { return; }
        ownHits += 1;
        hitPaddle();
    
        Paddle paddle = (Paddle) getIntersectingObjects(Paddle.class).get(0);
        turnAwayFrom(paddle.getX(), paddle.getY(), false);
    }
    
    private void hitPaddle() {
        GreenfootSound onPaddleHit = new GreenfootSound("BombBeep.mp3");
        onPaddleHit.play();
        increaseSpeed();
    }
    
    private void increaseSpeed() {
        pingWorld = (PingWorld) getWorld();
        if (ownHits % HITS_FOR_SPEED == 0) {
            speed *= 2;
            pingWorld.levelText((int) (ownHits / HITS_FOR_SPEED + 1));
        }
    }
    
    private void turnAwayFrom(int x, int y, boolean goingUp) {
        turnTowards(x, y);
        if (goingUp) { setRotation(Math.clamp((180 + getRotation()) % 360, 90 - MAX_ANGLE, 90 + MAX_ANGLE)); }
        else { setRotation(Math.clamp((180 + getRotation()) % 360, 270 - MAX_ANGLE, 270 + MAX_ANGLE)); }
    }
    
    private void init(boolean reset) {
        speed = 2;
        delay = DELAY_TIME;
        ownHits = 0;
        
        if (pingWorld != null) { pingWorld.levelText(1); }
        
        if (reset == false) { return; }
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }

}
