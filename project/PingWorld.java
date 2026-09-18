import greenfoot.*;

public class PingWorld extends World
{
    private Product skin = Shop.products[GameManager.getSkin()];
    private Paddle topPaddle = new Paddle(100, 20, 2, true, skin.images.getTopPaddle());
    private Paddle bottomPaddle = new Paddle(100, 20, 2, false, skin.images.getBottomPaddle());
    private Ball ball = new Ball(bottomPaddle, topPaddle, skin.images.getBall(), skin.sounds.getWallHit(), skin.sounds.getPaddleHit());
    private int runner = 0;
    private boolean gameStarted;
    private GreenfootSound ambient = skin.sounds.getAmbient();

    public PingWorld(boolean gameStarted) {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        this.gameStarted = gameStarted;
        }
    
    
    public void act() {
        runner++;
        if (runner == 1) {
            if (gameStarted) {
                speedText(1 + (float) (GameManager.getHighscore() - 10) / (float) ball.getHitsForSpeed());
                moneyText(new GameManager().getMoney());
                highscoreText(new GameManager().getHighscore());
                scoreText(GameManager.getHighscore() - 10);
                returnText();
                
                addBackground();
                
                addObject(topPaddle, getWidth() / 2, 50);
                addObject(bottomPaddle, getWidth() / 2, getHeight() - 50);
                addObject(ball, 0, 0);
                
                ambient.playLoop();
            }
        }
        
        if (Greenfoot.isKeyDown("Escape")) {
            ambient.stop();
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    private void addBackground() {
        GreenfootImage background = getBackground();
        GreenfootImage image = skin.images.getBackground();
        image.scale(getWidth(), getHeight());
        background.drawImage(image, 0, 0);
    }
    
    public void enableBottomAI() {
        bottomPaddle.setAI(true);
    }
    
    public void speedText(float x) { showText("Speed: " + Float.toString(x), 50, getHeight() - 16); }
    
    public void moneyText(int n) { showText("Money: " + Integer.toString(n), getWidth() - 70, getHeight() - 16); }
    
    public void highscoreText(int n) { showText("Highscore: " + Integer.toString(n), getWidth() - 70, 16); }
    
    public void scoreText(int n) { showText("Score: " + Integer.toString(n), getWidth() - 70, 38); }
    
    public void returnText() {showText("Press ESC to return to main menu", 105, 16); }
    
}