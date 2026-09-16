import greenfoot.*;

public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private GameManager gm = new GameManager();
    private Paddle aiPaddle = new Paddle(100,20, 2, true, gm.getActiveSkinPack().getSkins().get(0));
    private Paddle paddle = new Paddle(100,20, 2, false, gm.getActiveSkinPack().getSkins().get(0));
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
            moneyText(new GameManager().getMoney());
            returnText();
            background = new Background(getHeight(), getHeight(), gm.getActiveSkinPack().getSkins().get(2), gm.getActiveSkinPack().getSounds().get(4));
            addObject(background, getHeight()/2, getHeight()/2);
            addObject(aiPaddle, getWidth() / 2, 50);
            addObject(paddle, getWidth() / 2, getHeight() - 50);
            addObject(ball, 0, 0);
        }
        }
        
        String key = Greenfoot.getKey();
        if (key != null && key.equals("escape")) { background.stopBGSound(); Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    public void levelText(int n) { showText("Speed:" + Integer.toString(n), 40, getHeight() - 16); }
    
    public void moneyText(int n) { showText("Money:" + Integer.toString(n), getWidth() - 60, getHeight() - 16); }
    
    public void returnText() {showText("Press ESC to return to main menu", 105, 16); }
    
}