import greenfoot.*;
import java.util.*;

public class Product  
{
    private String packName;
    private int price;

    /* Images */
    private GreenfootImage botPaddle;
    private GreenfootImage topPaddle;
    private GreenfootImage backGround;
    private GreenfootImage ball;

    /* Sounds */
    private GreenfootSound wallhit;
    private GreenfootSound paddleHit;
    private GreenfootSound winSound;
    private GreenfootSound lossSound;
    private GreenfootSound backgroundSound;
    
    /**
     * Constructor for each skin / sound in a pack
     * @param pN Pack name
     * @param p Price
     * @param bP Bottom paddle
     * @param tP Top paddle
     * @param bG Background
     * @param b Ball
     * @param wH Wall-hit sound
     * @param pH Paddle hit sound
     * @param wS Win sound
     * @param lS Loss sound
     * @param bgS Ambient sound
     */
    public Product(String pN, int p, GreenfootImage bP, GreenfootImage tP, GreenfootImage bG, GreenfootImage b,
                   GreenfootSound wH, GreenfootSound pH, GreenfootSound wS, GreenfootSound lS, GreenfootSound bgS) 
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
        backgroundSound = bgS;
    }
    
    /* Return a list of skins in said pack */
    public List<GreenfootImage> getSkins() {
        List<GreenfootImage> imgs = new ArrayList<>();
        imgs.add(botPaddle);
        imgs.add(topPaddle);
        imgs.add(backGround);
        imgs.add(ball);
        return imgs;
        }
        
    /* Return a list of sounds in said pack */
    public List<GreenfootSound> getSounds() {
        List<GreenfootSound> sounds = new ArrayList<>();
        sounds.add(wallhit);
        sounds.add(paddleHit);
        sounds.add(winSound);
        sounds.add(lossSound);
        sounds.add(backgroundSound);
        return sounds;
    }
    
    /* Return price of said skinpack */
    public int getPrice() {
        return price;
    }
}

