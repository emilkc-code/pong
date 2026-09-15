import greenfoot.*;

public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private WorldSwappingButton shop_Button = new WorldSwappingButton("Shop", 200, 70);
    private WorldSwappingButton play_Button = new WorldSwappingButton("Ping", 200, 70);
    
    public IntroWorld() {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fillRect(0, 0, getWidth(), getHeight());
        
        addObject(new TextLabel("Slong.. (Ping) (Pong)", 40), getWidth() / 2, getHeight() / 6);
        
        //addObject(new TextLabel("Play", 30), getWidth() / 2, getHeight() - getHeight() / 3);
        //addObject(new TextLabel("Shop", 30), getWidth() / 2, getHeight() - getHeight() / 5);
        play_Button.setTextandColor("Play", 0, 0);        
        addObject(play_Button, 220, 450);
        shop_Button.setTextandColor("Shop", 0, 0);
        addObject(shop_Button, 220, 570);
    }
    
    /*public void act() {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter")) { Greenfoot.setWorld(new PingWorld(true)); }
    }*/
    
}
