import greenfoot.*;

public class Product {
    protected int price;
    protected int owned;
    
    protected ImageContainer images = new ImageContainer();
    protected SoundContainer sounds = new SoundContainer();
    
    public int getPrice() { return price; }

    public class ImageContainer {
        protected GreenfootImage product;
        protected GreenfootImage background;
        protected GreenfootImage ball;
        protected GreenfootImage bottomPaddle;
        protected GreenfootImage topPaddle;
        
        protected GreenfootImage getProduct()      { return product; }
        protected GreenfootImage getBackground()   { return background; }
        protected GreenfootImage getBall()         { return ball; }
        protected GreenfootImage getBottomPaddle() { return bottomPaddle; }
        protected GreenfootImage getTopPaddle()    { return topPaddle; }
    }

    public class SoundContainer {
        protected GreenfootSound ambient;
        protected GreenfootSound loss;
        protected GreenfootSound paddleHit;
        protected GreenfootSound wallHit;
        protected GreenfootSound win;
        
        protected GreenfootSound getAmbient()   { return ambient; }
        protected GreenfootSound getLoss()      { return loss; }
        protected GreenfootSound getPaddleHit() { return paddleHit; }
        protected GreenfootSound getWallHit()   { return wallHit; }
        protected GreenfootSound getWin()       { return win; }
    }
}