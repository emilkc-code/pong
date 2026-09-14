import greenfoot.*;

public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    public PingWorld(boolean gameStarted) {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        if (gameStarted) {
            levelText(1);
            
            addObject(new Background(getHeight(), getHeight()),getHeight()/2, getHeight()/2);
            addObject(new AIPaddle(100, 20, 1), 60, getHeight() - 650);
            addObject(new Paddle(100,20, 2), 60, getHeight() - 50);
            addObject(new Ball(getObjects(Paddle.class).get(0), getObjects(AIPaddle.class).get(0)), getHeight()/2, getHeight()/2);
        }
        
        else { Greenfoot.setWorld(new IntroWorld()); }
    }
    
    public void levelText(int n) { showText("Speed:" + Integer.toString(n), 40, 16); }
    
    public void moneyText(int n) { showText("Money:" + Integer.toString(n), getWidth() - 50, 16); }
    
}
