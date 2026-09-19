import greenfoot.*;

public class PingWorld extends World
{
    private Product skin = Shop.products[GameManager.getSkin()];
    private Paddle topPaddle = new Paddle(100, 20, 2, true, skin.images.getTopPaddle());
    private Paddle bottomPaddle = new Paddle(100, 20, 2, false, skin.images.getBottomPaddle());
    private Ball ball = new Ball(bottomPaddle, topPaddle);
    private int runner = 0;
    private boolean gameStarted;
    
    private GreenfootImage background = getBackground();
    private boolean backgroundReady = false;   // text is only drawn once the skin image is on the background
    
    // HUD labels, XOR-drawn straight onto the background (positions are the text's center)
    private XorLabel speedLabel     = new XorLabel(50,                getHeight() - 16);
    private XorLabel moneyLabel     = new XorLabel(getWidth() - 70,   getHeight() - 16);
    private XorLabel highscoreLabel = new XorLabel(getWidth() - 70,   16);
    private XorLabel scoreLabel     = new XorLabel(getWidth() - 70,   38);
    private XorLabel returnLabel    = new XorLabel(125,               16);

    public PingWorld(boolean gameStarted) {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        this.gameStarted = gameStarted;
    }
    
    public void act() {
        
        runner++;
        if (runner == 1) {
            if (gameStarted) {
                float speed = 1.0f;
                if (GameManager.getHighscore() >= 10) {
                    speed = (float) (GameManager.getHighscore() - 10) / (float) ball.getHitsForSpeed();
                    speed++;
                }
                
                // The background image must go down first, otherwise it would paint over the text
                addBackground();
                
                speedText(speed);
                
                moneyText(GameManager.getMoney());
                highscoreText(GameManager.getHighscore());
                
                int score = 0;
                if (GameManager.getHighscore() >= 10) { score = GameManager.getHighscore() - 10; }
                scoreText(score);
                
                returnText();
                
                addObject(topPaddle, getWidth() / 2, 50);
                addObject(bottomPaddle, getWidth() / 2, getHeight() - 50);
                addObject(ball, 0, 0);
            }
        }
        
        if (Greenfoot.isKeyDown("Escape")) {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    private void addBackground() {
        GreenfootImage image = skin.images.getBackground();
        image.scale(getWidth(), getHeight());
        background.drawImage(image, 0, 0);
        backgroundReady = true;
    }
    
    public void enableBottomAI() {
        bottomPaddle.setAI(true);
    }
    
    public void speedText(float x) { speedLabel.setText("Speed: " + Float.toString(x)); }
    
    public void moneyText(int n) { moneyLabel.setText("Money: " + Integer.toString(n)); }
    
    public void highscoreText(int n) { highscoreLabel.setText("Highscore: " + Integer.toString(n)); }
    
    public void scoreText(int n) { scoreLabel.setText("Score: " + Integer.toString(n)); }
    
    public void returnText() { returnLabel.setText("Press ESC to return to main menu"); }
    
    /**
     * A piece of text XOR-drawn onto the world background.
     * XOR is its own inverse, so changing the text means drawing the old string again
     * (which erases it, restoring the original pixels) and then drawing the new one.
     */
    private class XorLabel {
        private final int x, y;
        private String wanted = null;   // the text we want on screen
        private String drawn  = null;   // the text currently drawn on the background
        
        XorLabel(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        void setText(String text) {
            wanted = text;
            refresh();
        }
        
        private void refresh() {
            if (!backgroundReady) return;
            if (wanted == null || wanted.equals(drawn)) return;   // nothing new to draw
            
            if (drawn != null) { draw(drawn); }   // erase the old text
            draw(wanted);
            drawn = wanted;
        }
        
        private void draw(String text) {
            TextHelper.drawCenteredStringXOR(background, text, Fonts.getSmall(), x, y);
        }
    }
}