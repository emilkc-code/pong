import greenfoot.*;

public class Paddle extends Actor
{
    private final float DRAG_COEFFICIENT = 0.9f;
    private final float ACCELORATION_FORCE = 0.4f;
    
    private int width;
    private int height;
    private float positionX = 0;
    private int maxSpeed;
    private float velocity;
    private int acceleration = 0;
    private GreenfootImage image;

    public Paddle(int width, int height, int maxSpeed, GreenfootImage img) {
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.image = img;
        createImage();
    }
    
    public void act() {
        inputChecker();
        applyMovement();
    }

    private void inputChecker() {
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("right")) {
            acceleration = 0;
            velocity *= DRAG_COEFFICIENT;
        }
        
        if ((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) { acceleration = -1; }
        if ((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) { acceleration = 1; }
    }
    
    private void applyMovement() {
        velocity += acceleration * ACCELORATION_FORCE;
        if (this.getX() > getWorld().getWidth() - width / 2) { velocity = Math.clamp(velocity, -maxSpeed, 0); }
        if (this.getX() < width / 2) { velocity = Math.clamp(velocity, 0, maxSpeed); }
        velocity = Math.clamp(velocity, -maxSpeed, maxSpeed);
        positionX += velocity;
        setLocation((int) positionX, getY());
    }
    
    private void createImage() {
        image.scale(this.width, this.height);
        setImage(image);
    }

}
