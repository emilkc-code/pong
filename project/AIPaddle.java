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
    
    private boolean atLWall;
    private boolean atRWall;
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

        //tryChangeDirection();
        wallCollChecker();
        chaseBall();
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void chaseBall()
    {
        if (ball.getRotation() > 180) 
        {
            // if this x mindre end ball x, gå højre
            wallCollChecker();
            if (this.getX() < ball.getX() && !atRWall) {
                this.setLocation(getX() + speed, getY());
                atRWall = false;
            }
            // if this x højere end ball x, gå venstre
            if (this.getX() > ball.getX() && !atLWall) {
                this.setLocation(getX() - speed, getY());
                atLWall = false;

            }
            // hvis this x og ball x er lig. gør ingenting
        }
    }
    
    private void wallCollChecker()
    {
        //leftWall
        if ((this.getX() - this.width/2) <= 0){
            this.setLocation((this.getX() + this.width/2), getY());
            atLWall = true;
        }
        //rightWall
        if (((this.getX() + this.width/2) == getWorld().getWidth())){
            this.setLocation((this.getX() + this.width/2), getX());
            atRWall = true;
        }
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }

}
