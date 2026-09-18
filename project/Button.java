import greenfoot.*;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class Button extends Actor
{
    public static int borderThickness = 5;
    
    private String world = "";
    private int price = -1;
    private GreenfootImage image;

    public Button(String text, int width, int height) {
        image = new GreenfootImage(width, height);
        image.clear();
        TextHelper.drawCenteredString(image, text, Fonts.getNormal(), Colors.getText());
        draw();
        setImage(image);
    }
    
    public void act() {
        if (!Greenfoot.mouseClicked(this)) { return; }
        if (world != "") { switchWorld(); }
        
        if (price > GameManager.getMoney() || price < 0) { return; }
        buy();
    }
    
    private void draw() {
        switch (price) {
            case -1: drawButtonBorder(Colors.getButtonBorder()); break;
            case -2:
                image.clear();
                drawButton(Colors.getOwnedButton());
                TextHelper.drawCenteredString(image, "Owned", Fonts.getNormal(), Colors.getText());
                break;
            default:
                image.clear();
                drawButton(getNewColor());
                TextHelper.drawCenteredString(image, Integer.toString(price), Fonts.getNormal(), Colors.getText());
        }
    }
    
    private Color getNewColor() {
        if (price > GameManager.getMoney())  { return Colors.getCantBuyButton(); }
        else { return Colors.getCanBuyButton(); }
    }
    
    private void drawButton(Color color) {
        image.setColor(color);
        image.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
    
    private void drawButtonBorder(java.awt.Color color) { TextHelper.drawThickRect(image, borderThickness, color); }
    
    public void setPrice(int price) {
        this.price = price;
        draw();
    }
    
    public void buy() {
        GameManager.setMoney(GameManager.getMoney() - price);
        price = -2;
        draw();
        Shop shop = (Shop) getWorld();
        shop.drawMoney();
    }
    
    public void setWorld(String world) { this.world = world; }
    
    public void switchWorld(){
        switch (world) {
            case "IntroWorld": Greenfoot.setWorld(new IntroWorld());    break;
            case "Shop":       Greenfoot.setWorld(new Shop());          break;
            case "PingWorld":  Greenfoot.setWorld(new PingWorld(true)); break;
            case "PingWorldAI":
                PingWorld pingWorldAI = new PingWorld(true);
                Greenfoot.setWorld(pingWorldAI);
                //pingWorldAI.enableBottomAI();
        }
    }
}