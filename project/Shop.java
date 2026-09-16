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
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private static final GreenfootImage rocketLeagueLogo = new GreenfootImage("RLlogo.png");
    private static final GreenfootImage leagueOfLegendsLogo = new GreenfootImage("leagueLogo.jpg");
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
        new GreenfootSound("cs2LobbyMusic.mp3")));
        
        private TextLabel money_text = new TextLabel("Money", 38, new Color(220, 220, 220));
        private GameManager gm = new GameManager();
        private Button rlButton = new Button(gm.getActiveSkinPack().getPrice(), false, 150, 50, products.get(0));
        private Button llButton = new Button(gm.getActiveSkinPack().getPrice(), false, 150, 50, products.get(0));
    /**
     * Constructor for objects of class Shop.
     * 
     */
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        GreenfootImage background = getBackground();
        background.setColor(new Color(35, 35, 35));
        background.fillRect(0, 0, getWidth(), getHeight());
        placeImages();
    }
    
    public void placeImages()
    {
        // Rocket League
        rocketLeagueLogo.scale(150,70);
        getBackground().drawImage(rocketLeagueLogo, 70, 100);
        addObject(rlButton, 145, 190);
        
        // League of legends
        leagueOfLegendsLogo.scale(150,70);
        getBackground().drawImage(leagueOfLegendsLogo, 300, 100);
        addObject(llButton, 375, 190);
        
        // Menu button
        WorldSwappingButton menuButton = new WorldSwappingButton("IntroWorld", 500, 70);
        menuButton.setTextandColor("Return To Main Menu", 100, 45);
        addObject(menuButton, 250, 550);

        // Shop display
        TextLabel shop_text = new TextLabel("Shop", 38, new Color(220, 220, 220));
        //shop_text.setBlackColor("Shop", 38);
        addObject(shop_text, 250, 50);
        
        // Money Display
        money_text.setText("Money: " + Integer.toString(gm.getMoney()));
        money_text.setColor(new Color(220, 220, 220));
        addObject(money_text, 400, 50);
    }
    
    public void updateAll() {
        // Upd money
        money_text.setText("Money: " + Integer.toString(gm.getMoney()));
        addObject(money_text, 400, 50);
        if (rlButton.getCost() > gm.getMoney()) { rlButton.setColor(Color.RED); }
    }
}
