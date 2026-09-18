import greenfoot.*;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class Button extends Actor
{
    public static int borderThickness = 5;
    
    private String world = "";
    
    private int productIndex = -1;
    private boolean canBuy = true;
    
    private GreenfootImage image;

    public Button(String text, int width, int height) {
        image = new GreenfootImage(width, height);
        drawNew(text);
        setImage(image);
    }
    
    public void act() {
        if (!Greenfoot.mouseClicked(this)) { return; }
        
        if (world != "") { switchWorld(); return; }
        
        if (productIndex < 0) { return; }
        Shop shop = (Shop) getWorld();
        shop.tryBuy(productIndex, this);
    }
    
    public void drawNew(String text) {
        image.clear();
        
        if (productIndex == -1) { drawButtonBorder(Colors.getButtonBorder()); }
        else                    { drawButton(canBuy); }
        
        TextHelper.drawCenteredString(image, text, Fonts.getNormal(), Colors.getText());
    }
    
    private void drawButton(boolean canBuy) {
        Color color = Colors.getCantBuyButton();
        if (canBuy) { color = Colors.getCanBuyButton(); }
        
        image.setColor(color);
        image.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
    
    private void drawButtonBorder(java.awt.Color color) { TextHelper.drawThickRect(image, borderThickness, color); }
    
    public void setProductIndex(int n) { productIndex = n; }
    public void setCanBuy(boolean canBuy) { this.canBuy = canBuy; }
    
    public void setWorld(String world) { this.world = world; }
    
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