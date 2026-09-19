import greenfoot.*;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class Button extends UI
{
    private String world = "";
    private int productIndex = -1;
    private GreenfootImage image;
    
    public void setProductIndex(int n) { productIndex = n; }
    public void setWorld(String world) { this.world = world; }

    public Button(int width, int height) {
        image = new GreenfootImage(width, height);
        setImage(image);
    }
    
    public void act() {
        if (!Greenfoot.mouseClicked(this)) { return; }
        
        if (world != "") { switchWorld(); return; }
        
        if (productIndex < 0) { return; }
        Shop shop = (Shop) getWorld();
        shop.tryBuy(productIndex, this);
    }
    
    public void switchWorld(){
        switch (world) {
            case "IntroWorld": Greenfoot.setWorld(new IntroWorld());    break;
            case "Shop":       Greenfoot.setWorld(new Shop());          break;
            case "PingWorld":  Greenfoot.setWorld(new PingWorld(true)); break;
            case "PingWorldAI":
                PingWorld pingWorldAI = new PingWorld(true);
                Greenfoot.setWorld(pingWorldAI);
                pingWorldAI.enableBottomAI();
        }
    }
}