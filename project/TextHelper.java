import greenfoot.*;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;

public class TextHelper  
{
    /** Converts a greenfoot.Color to a java.awt.Color. */
    public static java.awt.Color toAwt(Color color) {
        return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    /** Converts a greenfoot.Font to a java.awt.Font. */
    public static java.awt.Font toAwt(greenfoot.Font font) {
        int style = java.awt.Font.PLAIN;
        if (font.isBold()) style |= java.awt.Font.BOLD;
        if (font.isItalic()) style |= java.awt.Font.ITALIC;
        return new java.awt.Font(font.getName(), style, font.getSize());
    }
    
    private static Graphics2D createGraphics(GreenfootImage img) {
        Graphics2D g2d = img.getAwtImage().createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,      RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        return g2d;
    }
    
    /** Draws text centered in the image. */
    public static void drawCenteredString(GreenfootImage img, String text, greenfoot.Font font, Color color) {
        drawCenteredString(img, text, font, color, img.getWidth() / 2, img.getHeight() / 2);
    }
    
    /** Draws text centered on the point (centerX, centerY) of the image. */
    public static void drawCenteredString(GreenfootImage img, String text, greenfoot.Font font, Color color, int centerX, int centerY) {
        Graphics2D g2d = createGraphics(img);
        g2d.setFont(toAwt(font));
        g2d.setColor(toAwt(color));
        
        FontMetrics metrics = g2d.getFontMetrics();
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY + (metrics.getAscent() - metrics.getDescent()) / 2;  // baseline that visually centers the text
        
        g2d.drawString(text, x, y);
        g2d.dispose();
    }
    
    /**
     * Draws text centered on (centerX, centerY), XOR-ing it with whatever is already in the image.
     * With white as the draw color and black as the XOR color, every pixel the text covers is
     * inverted (dst ^ 0xFFFFFF), so it stays readable on any background.
     */
    public static void drawCenteredStringXOR(GreenfootImage img, String text, greenfoot.Font font, int centerX, int centerY) {
        Graphics2D g2d = createGraphics(img);
        // Antialiasing blends partial pixels, which gives garbage edges in XOR mode
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2d.setFont(toAwt(font));
        
        FontMetrics metrics = g2d.getFontMetrics();
        int x = centerX - metrics.stringWidth(text) / 2;
        int y = centerY + (metrics.getAscent() - metrics.getDescent()) / 2;
        
        g2d.setColor(java.awt.Color.WHITE);
        g2d.setXORMode(java.awt.Color.BLACK);
        g2d.drawString(text, x, y);
        g2d.dispose();
    }
    
    /**
     * Draws a rectangular border of exactly `thickness` pixels. The border lies entirely INSIDE the
     * given box, so the box's outer size is exactly width x height (nothing spills out or gets clipped).
     */
    private static void drawBorder(GreenfootImage img, int x, int y, int width, int height, int thickness, java.awt.Color color) {
        Graphics2D g2d = img.getAwtImage().createGraphics();
        
        Area border = new Area(new Rectangle(x, y, width, height));
        border.subtract(new Area(new Rectangle(x + thickness, y + thickness, width - 2 * thickness, height - 2 * thickness)));
        
        g2d.setColor(color);
        g2d.fill(border);
        g2d.dispose();
    }
    
    private static void drawBorderXOR(GreenfootImage img, int x, int y, int width, int height, int thickness, java.awt.Color color) {
        Graphics2D g2d = img.getAwtImage().createGraphics();
        
        Area border = new Area(new Rectangle(x, y, width, height));
        border.subtract(new Area(new Rectangle(x + thickness, y + thickness, width - 2 * thickness, height - 2 * thickness)));
        
        g2d.setColor(java.awt.Color.WHITE);
        g2d.setXORMode(java.awt.Color.BLACK);
        g2d.fill(border);
        g2d.dispose();
    }
    
    /** Draws a border of the given size, centered on (centerX, centerY) of the image. */
    public static void drawThickRectAt(GreenfootImage img, int centerX, int centerY, int width, int height, int thickness, java.awt.Color color, boolean inverted) {
        if (inverted) {
            drawBorderXOR(img, centerX - width / 2, centerY - height / 2, width, height, thickness, color);
            return;
        }
        
        drawBorder(img, centerX - width / 2, centerY - height / 2, width, height, thickness, color);
    }
    
    /** Draws a border around the edge of the whole image. */
    public static void drawThickRect(GreenfootImage img, int thickness, java.awt.Color color) {
        drawBorder(img, 0, 0, img.getWidth(), img.getHeight(), thickness, color);
    }
}