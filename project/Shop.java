import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shop extends World
{
    //Instance variables
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    // Text modefiers
    private Font titleFont = new greenfoot.Font("Arial", true, false, 48);
    private Font font = new greenfoot.Font("Arial", true, false, 30);
    private Color textColor = new Color(220, 220, 220);
    
    private GameManager gm = new GameManager();
    

    // Logos
    private static final GreenfootImage rocketLeagueLogo = new GreenfootImage("RLlogo.png");
    private static final GreenfootImage cs = new GreenfootImage("mir.jpg");
    // product list containing a Rocket League pack and a CSGO pack (Skins/Sounds)
    private static final List<Product> products = List.of(
    new Product(
        "Rocket League pack", 
        150,
        new GreenfootImage("RLcar1.png"), 
        new GreenfootImage("RLcar2.png"), 
        new GreenfootImage("RLbg.png"), 
        new GreenfootImage("RLball.png"), 
        new GreenfootSound("rlWHso.mp3"), 
        new GreenfootSound("rlPHso.mp3"), 
        new GreenfootSound("rlWso.mp3"), 
        new GreenfootSound("rlLso.mp3"),
        new GreenfootSound("cs2LobbyMusic.mp3")),
            new Product(
                "CSGO", 
                150,
                new GreenfootImage("ct.png"), 
                new GreenfootImage("ak.png"), 
                new GreenfootImage("mir.jpg"), 
                new GreenfootImage("c4.png"), 
                new GreenfootSound("WallThudSound.mp3"), 
                new GreenfootSound("BombBeep.mp3"), 
                new GreenfootSound("WinningRobloxOldWinningSoundEffect.mp3"), 
                new GreenfootSound("WinningRobloxOldWinningSoundEffect.mp3"),
                new GreenfootSound("cs2LobbyMusic.mp3")));
        
        private WorldSwappingButton money_text = new WorldSwappingButton(false, "NULL", "Money: " + Integer.toString(gm.getMoney()), 200, 50, font, textColor);
        
                
        // Buttons and labels
        private TextLabel money_text = new TextLabel("Money", 38, new Color(220, 220, 220));
        private GameManager gm = new GameManager();
        private Button rlButton = new Button(products.get(0).getPrice(), false, 150, 50, products.get(0));
        private Button csButton = new Button(products.get(1).getPrice(), false, 150, 50, products.get(1));
   
    // Constructor - Sets background and places all images and alike.    
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        GreenfootImage background = getBackground();
        background.setColor(new Color(35, 35, 35));
        background.fillRect(0, 0, getWidth(), getHeight());
        placeImages();
    }
    
    // Function for making images and buttons and alike
    public void placeImages()
    {
        // Rocket League
        rocketLeagueLogo.scale(150,70);
        getBackground().drawImage(rocketLeagueLogo, 70, 100);
        addObject(rlButton, 145, 190);
        WorldSwappingButton rl_money = new WorldSwappingButton(false, "NULL", Integer.toString(rlButton.getCost()), 350, 70, font, textColor);
        addObject(rl_money, 145, 240);
        WorldSwappingButton cs_money = new WorldSwappingButton(false, "NULL", Integer.toString(csButton.getCost()), 350, 70, font, textColor);
        addObject(cs_money, 375, 240);
        
        // League of legends
        cs.scale(150,70);
        getBackground().drawImage(cs, 300, 100);
        addObject(csButton, 375, 190);
        
        // Menu button
        WorldSwappingButton menuButton = new WorldSwappingButton(true, "IntroWorld", "Return To Main Menu", 350, 70, font, textColor);
        addObject(menuButton, 250, getHeight() - 80);

        // Shop display
        WorldSwappingButton shop_text = new WorldSwappingButton(false, "NULL", "Shop", 150, 70, titleFont, textColor);
        //shop_text.setBlackColor("Shop", 38);
        addObject(shop_text, 70, 40);
        
        // Money Display
        addObject(money_text, getWidth() / 2, getHeight() - 150);
    }
    
    //Update all function, typically called by buttons
    public void updateAll() {
        // Upd money
        money_text.drawCenteredString(false, money_text.getImage(), "Money: " + Integer.toString(gm.getMoney()), 200, 50, font, textColor);
        if (rlButton.getCost() > gm.getMoney()) { rlButton.setColor(Color.RED); }
    }
}
