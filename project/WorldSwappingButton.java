import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/* imports for functionality */
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class WorldSwappingButton extends Actor
{
    /* instance variables */
    private String targetWorldName;
    private GreenfootImage img;

/**
 * Constructor
 * @param button
 * @param targetWorldName
 * @param text
 * @param width
 * @param height
 * @param font
 * @param color
 */
public WorldSwappingButton(Boolean button, String targetWorldName, String text, int width, int height, Font font, Color color)
    {
        this.targetWorldName = targetWorldName;
        
        img = new GreenfootImage(width, height);
        drawCenteredString(button, img, text, width, height, font, color);
        setImage(img);
    }
    
    public void drawCenteredString2() {
        return;
    }
    
    /**
     * Check if mouse clicked this button and act accordingly
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            switch (targetWorldName) {
                case "IntroWorld":
                    Greenfoot.setWorld(new IntroWorld());
                    break;
                case "Shop":
                    Greenfoot.setWorld(new Shop());
                    break;
                case "PingWorld":
                    PingWorld pingWorld = new PingWorld(true);
                    Greenfoot.setWorld(pingWorld);
                    break;
                case "PingWorldAI":
                    PingWorld pingWorldAI = new PingWorld(true);
                    Greenfoot.setWorld(pingWorldAI);
                    pingWorldAI.enableBottomAI();
            }
        }
    }
    
    /* Draw the string centered */
    public void drawCenteredString(boolean button, GreenfootImage img, String text, int rectWidth, int rectHeight, greenfoot.Font font, Color color) {
        /* Convert greenfoot.Font to java.awt.Font behind the scenes just to measure the text */
        int style = java.awt.Font.PLAIN;
        if (font.isBold()) style |= java.awt.Font.BOLD;
        if (font.isItalic()) style |= java.awt.Font.ITALIC;
        java.awt.Font awtFont = new java.awt.Font(font.getName(), style, font.getSize());

        /* Measure the text */
        Graphics2D g2d = (Graphics2D) img.getAwtImage().createGraphics();
        FontMetrics metrics = g2d.getFontMetrics(awtFont);
        g2d.dispose(); // Clean up memory

        /* Calculate the centered coordinates */
        int x = (rectWidth - metrics.stringWidth(text)) / 2;
        int y = (int) (rectHeight * 0.65);

        /* Apply the Greenfoot font and draw */
        img.setFont(font);
        img.clear();
        if (button) { drawThickRect(img, rectWidth, rectHeight, 5, new java.awt.Color(150, 150, 150)); }
        img.setColor(color);
        img.drawString(text, x, y);
    }
    
    public void drawThickRect(GreenfootImage img, int width, int height, int thickness, java.awt.Color color) {
        /* Get the AWT graphics context from the GreenfootImage */
        Graphics2D g2d = (Graphics2D) img.getAwtImage().createGraphics();

        /* Set the color and line thickness (stroke) */
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));

        /* Draw the rectangle using a standard AWT Rectangle object */
        g2d.draw(new Rectangle(0, 0, width, height));

        /* Clean up memory */
        g2d.dispose();
    }
}
