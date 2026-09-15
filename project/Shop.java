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
        50, 
        new GreenfootImage("RLcar1.png"), 
        new GreenfootImage("RLcar2.png"), 
        new GreenfootImage("RLbg.png"), 
        new GreenfootImage("RLball.png"), 
        new GreenfootSound("rlWHso.mp3"), 
        new GreenfootSound("rlPHso.mp3"), 
        new GreenfootSound("rlWso.mp3"), 
        new GreenfootSound("rlLso.mp3"),
        new GreenfootSound("cs2LobbyMusic.mp3")));
        
    /**
     * Constructor for objects of class Shop.
     * 
     */
    public Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        placeImages();
    }
    
    public void placeImages()
    {
        // Rocket League
        rocketLeagueLogo.scale(150,70);
        getBackground().drawImage(rocketLeagueLogo, 70, 100);
        addObject(new Button(10, false, 150, 50, products.get(0)), 145, 190);
        
        // League of legends
        leagueOfLegendsLogo.scale(150,70);
        getBackground().drawImage(leagueOfLegendsLogo, 300, 100);
        addObject(new Button(10, false, 150, 50, products.get(0)), 375, 190);
        
    }
}
