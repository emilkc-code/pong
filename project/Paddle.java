import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width;
    private int height;
    private int dx;
    
    private boolean atLWall;
    private boolean atRWall;
    private int speed;

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height, int sped)
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
        //tryChangeDirection();
        wallCollChecker();
        inputChecker();
        //setLocation(getX() + dx, getY());
    }    

    /**
     * Will rotate the paddle 180 degrees if the paddle is at worlds edge.
     */
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }

    private void inputChecker()
    {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left") && !atLWall) {
            this.setLocation(getX() - speed, getY());
            atRWall = false;
        }
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right") && !atRWall) {
            this.setLocation(getX() + speed, getY());
            atLWall = false;
        }
        
    }
    
    private void wallCollChecker()
    {
        //leftWall
        if ((this.getX() - this.width/2) == 0){
            atLWall = true;
        }
        //rightWall
        if (((this.getX() + this.width/2) == getWorld().getWidth())){
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
