import greenfoot.*;
import java.util.*;
/**
 * Write a description of class Product here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Product  
{
    private String packName;
    private int price;
    // Images
    private GreenfootImage botPaddle;
    private GreenfootImage topPaddle;
    private GreenfootImage backGround;
    private GreenfootImage ball;
    // Sounds
    private GreenfootSound wallhit;
    private GreenfootSound paddleHit;
    private GreenfootSound winSound;
    private GreenfootSound lossSound;
    
    // Constructor 
    public Product(String pN, int p, GreenfootImage bP, GreenfootImage tP, GreenfootImage bG, GreenfootImage b,
                   GreenfootSound wH, GreenfootSound pH, GreenfootSound wS, GreenfootSound lS) 
    {
        packName = pN;
        price = p;
        botPaddle = bP;
        topPaddle = tP;
        backGround = bG;
        ball = b;
        wallhit = wH;
        paddleHit = pH;
        winSound = wS;
        lossSound = lS;
    }
    
    public List<GreenfootImage> getSkins() {
        List<GreenfootImage> imgs = new ArrayList<>();
        imgs.add(botPaddle);
        imgs.add(topPaddle);
        imgs.add(backGround);
        imgs.add(ball);
        return imgs;
        }
        
    public List<GreenfootSound> getSounds() {
        List<GreenfootSound> sounds = new ArrayList<>();
        sounds.add(wallhit);
        sounds.add(paddleHit);
        sounds.add(winSound);
        sounds.add(lossSound);
        return sounds;
    }
}

