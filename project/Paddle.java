import greenfoot.*;

public class Paddle extends Actor
{
    /* Instance variables */
    private final float DRAG_COEFFICIENT = 0.9f;
    private final float ACCELORATION_FORCE = 0.8f;
    
    private boolean isAI;
    private double targetPoint = -1;
    private Ball ball;
    
    private int width;
    private int height;
    private float positionX = -1;
    private int maxSpeed;
    private float velocity;
    private int acceleration = 0;
    private GreenfootImage image;
    

    /**
     * Paddle constructor with basic essentials
     * @param width
     * @param height
     * @param maxSpeed
     * @param isAI
     */
    public Paddle(int width, int height, int maxSpeed, boolean isAI, GreenfootImage img) {
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.isAI = isAI;
        this.image = img;
        createImage();
    }
    
    /* method that runs every frame checking input and executing "movement" */
    public void act() {
        if (!isAI) {
            if ( getWorld() != null && positionX == -1 ) { positionX = getWorld().getWidth() / 2; }
            
            inputChecker();
            applyMovement();
        } else {
            if (ball == null || targetPoint < 0) { return; }
            moveHere((int) targetPoint);
        }
    }
    
    /**
     * Sets ball to target object ball
     * @param ball
     */
    public void setBall(Ball ball) { this.ball = ball; }
    
    /**
     * Returns target ball
     */
    public Ball getBall() { return ball; }
    
    /**
     * Sets AI
     */
    public void setAI(boolean b) { isAI = b; }
    
    /**
     * Returns is air or not
     */
    public boolean isAI() { return isAI; }
    
    /**
     * Move to position
     */
    private void moveHere(int n) {
        if (this.getX() < n
         && this.getX() < getWorld().getWidth() - width / 2) {
             this.setLocation((int) (getX() + maxSpeed), getY());
        }
        
        if (this.getX() > n
         && this.getX() > width / 2) {
             this.setLocation((int) (getX() - maxSpeed), getY());
        }
    }
    
    /**
     * Reset to center
     */
    public void resetToCenter() {
        targetPoint = getWorld().getWidth() / 2;
    }
    
    /**
     * Logic for predicting ball movement via function and targeting endpoint.
     */
    public void setupPredictor(Ball ball) {
        int ballHeight = ball.getY();
        int ballRotation = ball.getRotation();
        
        if ( getY() > getWorld().getHeight() / 2 ) {
            ballHeight = getWorld().getHeight() - ballHeight;
            ballRotation = 360 - ballRotation;
        }
        
        double width = getWorld().getWidth() - ball.getSize();
        double height = getWorld().getHeight() - 60 - ball.getSize() / 2 - (getWorld().getHeight() - ballHeight);
        double startPosition = ball.getX() / width;
        double predictionPoint = height / width;
        
        targetPoint = predict(ballRotation - 90, startPosition, predictionPoint);
        targetPoint = (1 - targetPoint) * width + ball.getSize() / 2;
        targetPoint = Math.round(targetPoint);
        
        double snipe = ball.getX() - getWorld().getWidth() / 2;
        snipe /= width / 2;
        targetPoint += snipe * 10;
    }
    
    /**
     * execution logic for endpoint.
     */
    private double predict(double angle, double startPosition, double predictionPoint) {
        angle = Math.toRadians(angle);
        double a = Math.sin(-angle) / Math.cos(-angle);
        double count = a * predictionPoint + 1 - startPosition;
        double flipper = Math.pow(-1, Math.floor(count));
        double result = trueModulo(count, 1) * flipper + 0.5 + 0.5 * (-1) * flipper;
        return result;
    }
    
    /**
     * Math function method
     */
    private double trueModulo(double a, double b) { return a - b * Math.floor(a / b); }

    /**
     * Checks for inputs such as movement left and right
     */
    private void inputChecker() {
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("right")) {
            acceleration = 0;
            velocity *= DRAG_COEFFICIENT;
        }
        
        if ((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) { acceleration = -1; }
        if ((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) { acceleration = 1; }
    }
    
    /**
     * Apply the movement of paddle
     */
    private void applyMovement() {
        velocity += acceleration * ACCELORATION_FORCE;
        if (this.getX() > getWorld().getWidth() - width / 2) { velocity = Math.clamp(velocity, -maxSpeed, 0); }
        if (this.getX() < width / 2) { velocity = Math.clamp(velocity, 0, maxSpeed); }
        velocity = Math.clamp(velocity, -maxSpeed, maxSpeed);
        positionX += velocity;
        setLocation((int) positionX, getY());
    }
    
    /**
     * Creates the image of said paddle
     */
    private void createImage() {
        image.scale(this.width, this.height);
        setImage(image);
    }

}
