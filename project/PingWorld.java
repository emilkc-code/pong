import greenfoot.*;

public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    private AIPaddle aiPaddle = new AIPaddle(100, 20, 2);
    private Paddle paddle = new Paddle(100,20, 2);
    private Ball ball = new Ball(paddle, aiPaddle);
    
    public PingWorld(boolean gameStarted) {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        if (gameStarted) {
            levelText(1);
            
            addObject(new Background(getHeight(), getHeight()),getHeight()/2, getHeight()/2);
            addObject(aiPaddle, 60, 50);
            addObject(paddle, 60, getHeight() - 50);
            addObject(ball, 0, 0);
        }
        
        else { Greenfoot.setWorld(new IntroWorld()); }
    }
    
    public void levelText(int n) { showText("Speed:" + Integer.toString(n), 40, 16); }
    
    public void moneyText(int n) { showText("Money:" + Integer.toString(n), getWidth() - 50, 16); }
    
}