import greenfoot.*;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

public class TextHelper  
{
    public static void drawCenteredString(GreenfootImage img, String text, greenfoot.Font font, Color color) {
        int style = java.awt.Font.PLAIN;
        if (font.isBold()) style |= java.awt.Font.BOLD;
        if (font.isItalic()) style |= java.awt.Font.ITALIC;
        java.awt.Font awtFont = new java.awt.Font(font.getName(), style, font.getSize());
        
        Graphics2D g2d = (Graphics2D) img.getAwtImage().createGraphics();
        FontMetrics metrics = g2d.getFontMetrics(awtFont);
        g2d.dispose();
        
        int x = (img.getWidth() - metrics.stringWidth(text)) / 2;
        int y = (int) (img.getHeight() * 0.5 + metrics.getHeight() / 4);
        
        img.setFont(font);
        img.setColor(color);
        img.drawString(text, x, y);
    }
    
    public static void drawThickRect(GreenfootImage img, int thickness, java.awt.Color color) {
        Graphics2D g2d = (Graphics2D) img.getAwtImage().createGraphics();
        
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        
        g2d.draw(new Rectangle(0, 0, img.getWidth(), img.getHeight()));
        
        g2d.dispose();
    }
}
