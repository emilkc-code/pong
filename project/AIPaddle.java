import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class AIPaddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    
    private int speed;
    private Ball ball;
    private boolean firstActRunned = false;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public AIPaddle(int width, int height, int sped)
    {
        this.width = width;
        this.height = height;
        this.speed = sped;
        dx = 1;
        createImage();
        
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!firstActRunned)
        { 
            this.ball = (Ball) getWorld().getObjects(Ball.class).get(0);
            firstActRunned = true;
        }
        chaseBall();
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void chaseBall()
    {
        if (ball.getRotation() > 180) 
        {
            if (this.getX() < ball.getX() && this.getX() < getWorld().getWidth() - width / 2) {
                this.setLocation(getX() + speed, getY());
            }
            // if this x højere end ball x, gå venstre
            if (this.getX() > ball.getX() && this.getX() > width / 2) {
                this.setLocation(getX() - speed, getY());

            }
            // hvis this x og ball x er lig. gør ingenting
        }
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage img = new GreenfootImage("ak.png");
        img.scale(this.width, this.height);
        setImage(img);
    }

}
