import greenfoot.*;

public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private GameManager gm = new GameManager();
    private AIPaddle aiPaddle = new AIPaddle(100, 20, 1, gm.getActiveSkinPack().getSkins().get(1));
    private Paddle paddle = new Paddle(100,20, 2, gm.getActiveSkinPack().getSkins().get(0));
    private Ball ball = new Ball(paddle, aiPaddle, gm.getActiveSkinPack().getSkins().get(3), gm.getActiveSkinPack().getSounds().get(0), gm.getActiveSkinPack().getSounds().get(1));
    private Background background;
    private int runner = 0;
    private boolean gameStarted;
    public PingWorld(boolean gamestarted) {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        this.gameStarted = gamestarted;
        }
    
    
    public void act() {
        runner++;
        if (runner == 1) {
            if (gameStarted) {
            levelText(1);
            returnText();
            background = new Background(getHeight(), getHeight(), gm.getActiveSkinPack().getSkins().get(2), gm.getActiveSkinPack().getSounds().get(4));
            addObject(background, getHeight()/2, getHeight()/2);
            addObject(aiPaddle, 60, 50);
            addObject(paddle, 60, getHeight() - 50);
            addObject(ball, 0, 0);
        }
        }
        
        String key = Greenfoot.getKey();
        if (key != null && key.equals("escape")) { background.stopBGSound(); Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    public void levelText(int n) { showText("Speed:" + Integer.toString(n), 40, 16); }
    
    public void moneyText(int n) { showText("Money:" + Integer.toString(n), getWidth() - 50, 16); }
    
    public void returnText() {showText("Press ESC to return to main menu", getWidth()/2 - 10, 16); }
    
}