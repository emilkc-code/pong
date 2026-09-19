import greenfoot.*;

public class IntroWorld extends World
{
    private static final int BUTTON_WIDTH  = 100;
    private static final int BUTTON_HEIGHT = 60;
    private static final int BORDER_THICKNESS = 3;
    
    private GreenfootImage background = getBackground();
    
    public IntroWorld() {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        Greenfoot.setSpeed(58);
        SaveManager.loadData();
        SoundManager.playAmbient();
        
        background.setColor(Colors.getBackground());
        background.fillRect(0, 0, getWidth(), getHeight());
        
        Product skin = Shop.products[GameManager.getSkin()];
        GreenfootImage image = new GreenfootImage(skin.images.getBackground());
        image.scale(getWidth(), getHeight());
        background.drawImage(image, 0, 0);
        
        // Everything below is drawn onto the background, so the skin image must already be there
        drawText("Ping", Fonts.getTitle(), getWidth() / 2, 150);
        drawText("Highscore: " + Integer.toString(GameManager.getHighscore()), Fonts.getNormal(), getWidth() / 2, 200);
        
        addMenuButton("Play",  "PingWorld",   getWidth() / 2, getHeight() - 250);
        addMenuButton("Watch", "PingWorldAI", getWidth() / 2, getHeight() - 180);
        addMenuButton("Shop",  "Shop",        getWidth() / 2, getHeight() - 50);
    }
    
    /** XOR-draws text straight onto the world background. */
    private void drawText(String text, greenfoot.Font font, int x, int y) {
        TextHelper.drawCenteredStringXOR(background, text, font, x, y);
    }
    
    /** Draws a bordered label on the background and places an invisible Button actor over it to handle clicks. */
    private void addMenuButton(String label, String worldName, int x, int y) {
        TextHelper.drawThickRectAt(background, x, y, BUTTON_WIDTH, BUTTON_HEIGHT, BORDER_THICKNESS, Colors.getTextBorder(), true);
        drawText(label, Fonts.getNormal(), x, y);
        
        Button button = new Button(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setWorld(worldName);
        addObject(button, x, y);
    }
}