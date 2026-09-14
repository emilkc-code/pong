import greenfoot.*;

public class Paddle extends Actor
{
    private final float DRAG_COEFFICIENT = 0.9f;
    private final float ACCELORATION_FORCE = 0.1f;
    
    private int width;
    private int height;
    private float positionX = 0;
    private int maxSpeed;
    private float velocity;
    private int acceloration = 0;

    public Paddle(int width, int height, int maxSpeed) {
        this.width = width;
        this.height = height;
        //positionX = getX();
        this.maxSpeed = maxSpeed;
        createImage();
    }
    
    public void act() {
        inputChecker();
        applyMovement();
    }

    private void inputChecker() {
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("right")) {
            acceloration = 0;
            velocity *= DRAG_COEFFICIENT;
        }
        
        if ((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) { acceloration = -1; }
        if ((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) { acceloration = 1; }
    }
    
    private void applyMovement() {
        velocity += acceloration * ACCELORATION_FORCE;
        if (this.getX() > getWorld().getWidth() - width / 2) { velocity = Math.clamp(velocity, -maxSpeed, 0); }
        if (this.getX() < width / 2) { velocity = Math.clamp(velocity, 0, maxSpeed); }
        velocity = Math.clamp(velocity, -maxSpeed, maxSpeed);
        positionX += velocity;
        setLocation((int) positionX, getY());
    }
    
    private void createImage() {
        GreenfootImage img = new GreenfootImage("ct.png");
        img.scale(this.width, this.height);
        setImage(img);
    }

}
