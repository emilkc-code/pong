import greenfoot.*;

public class Product {
    protected int price;
    protected boolean owned;
    
    protected ImageContainer images = new ImageContainer();
    protected SoundContainer sounds = new SoundContainer();
    
    public int getPrice() { return price; }
    
    public boolean isOwned()        { return owned; }
    public void setOwned(boolean b) { owned = b; }

    public class ImageContainer {
        protected GreenfootImage product;
        protected GreenfootImage background;
        protected GreenfootImage ball;
        protected GreenfootImage bottomPaddle;
        protected GreenfootImage topPaddle;
        
        public GreenfootImage getProduct()      { return product; }
        public GreenfootImage getBackground()   { return background; }
        public GreenfootImage getBall()         { return ball; }
        public GreenfootImage getBottomPaddle() { return bottomPaddle; }
        public GreenfootImage getTopPaddle()    { return topPaddle; }
    }

    public class SoundContainer {
        protected GreenfootSound ambient;
        protected GreenfootSound loss;
        protected GreenfootSound paddleHit;
        protected GreenfootSound wallHit;
        protected GreenfootSound win;
        
        public GreenfootSound getAmbient()   { return ambient; }
        public GreenfootSound getLoss()      { return loss; }
        public GreenfootSound getPaddleHit() { return paddleHit; }
        public GreenfootSound getWallHit()   { return wallHit; }
        public GreenfootSound getWin()       { return win; }
    }
}