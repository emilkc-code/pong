import greenfoot.*;

public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private Font font = new greenfoot.Font("Arial", true, false, 30);
    private Font titleFont = new greenfoot.Font("Arial", true, false, 48);
    private Color textColor = new Color(220, 220, 220);
    private WorldSwappingButton title_Button = new WorldSwappingButton(false, "NULL", "Ping", 120, 90, titleFont, textColor);
    private WorldSwappingButton highscore_Button = new WorldSwappingButton(false, "NULL", "Highscore: " + Integer.toString(new GameManager().getHighscore()), 230, 50, font, textColor);
    private WorldSwappingButton play_Button = new WorldSwappingButton(true, "PingWorld", "Play", 100, 60, font, textColor);
    private WorldSwappingButton watch_Button = new WorldSwappingButton(true, "PingWorldAI", "Watch", 100, 60, font, textColor);
    private WorldSwappingButton shop_Button = new WorldSwappingButton(true, "Shop", "Shop", 100, 60, font, textColor);
    
    public IntroWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(new Color(35, 35, 35));
        background.fillRect(0, 0, getWidth(), getHeight());
        
        //addObject(new TextLabel("Play", 30), getWidth() / 2, getHeight() - getHeight() / 3);
        //addObject(new TextLabel("Shop", 30), getWidth() / 2, getHeight() - getHeight() / 5); 
        addObject(title_Button, getWidth() / 2, 150);
        addObject(highscore_Button, getWidth() / 2, 200);
        addObject(play_Button, getWidth() / 2, getHeight() - 250);
        addObject(watch_Button, getWidth() / 2, getHeight() - 180);
        addObject(shop_Button, getWidth() / 2, getHeight() - 50);
    }
    
    /*public void act() {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter")) { Greenfoot.setWorld(new PingWorld(true)); }
    }*/
    
}
