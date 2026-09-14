import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private final int BALL_SIZE = 25;
    private final int BOUNCE_DEVIANCE_MAX = 5;
    private final int STARTING_ANGLE_WIDTH = 90;
    private final int DELAY_TIME = 100;
    private final int HITS_FOR_SPEED = 4;

    private int speed;
    private int ownHits;
    private boolean hasBouncedHorizontally;
    private boolean movingUpwards;
    private int delay;
    
    private Paddle bottomPaddle;
    private AIPaddle topPaddle;
    
    private PingWorld pingWorld;
    /**
     * Contructs the ball    and sets it in motion!
     */
    public Ball(Paddle bP, AIPaddle tP)
    {
        createImage();
        init(true);
        bottomPaddle = bP;
        topPaddle = tP;
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage(BALL_SIZE,BALL_SIZE);
        ballImage.setColor(Color.BLACK);
        ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddleBottom();
            checkBounceOffPaddleTop();
            checkRestart();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (!isTouchingCeiling() || !movingUpwards) { return; }
        
        revertVertically();
        movingUpwards = false;
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            init(true);
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        //int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation(180 - getRotation()); // 
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        //int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation(360 - getRotation());
    }

    private void checkBounceOffPaddleBottom()
    {
        if (getIntersectingObjects(Paddle.class).size() < 1 || movingUpwards) { return; }
        
        revertVertically();
        movingUpwards = true;
        ownHits += 1;
    
        pingWorld = (PingWorld) getWorld();
        if (ownHits % HITS_FOR_SPEED == 0) {
            speed *= 2;
            pingWorld.LevelText((int) (ownHits / HITS_FOR_SPEED + 1));
        }
    }
    
    private void checkBounceOffPaddleTop()
    {
    if (getIntersectingObjects(AIPaddle.class).size() < 1 || !movingUpwards) { return; }
    
    revertVertically();
    movingUpwards = false;
    }
    
    /**
     * Initialize the ball settings.
     */
    private void init(boolean reset)
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        movingUpwards = false;
        ownHits = 0;
        if (reset == true) {
            setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2); }
    }

}
