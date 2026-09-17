import greenfoot.*;

public class TextLabel extends Actor {
    /* Instance variables */
    private String text;
    private int fontSize;
    private Color color;
    
    /**
     * Constructor
     * @param text Text to display
     * @param fontSize Size of text
     * @param color Color of text
     */
    public TextLabel(String text, int fontSize, Color color) {
        this.text = text;
        this.fontSize = fontSize;
        this.color = color;
        updateText();
    }
    
    /* Update the text typically for shop updates */
    public void updateText() {
        int width = text.length() * (fontSize / 2) * 2;
        int height = fontSize + 10;
        
        GreenfootImage img = new GreenfootImage(width, height);
        Font font = new Font(true, false, fontSize);
        img.setFont(font);
        img.setColor(color);
        
        img.drawString(text, width / 4, fontSize);
        
        setImage(img);
    }
    
    /* set new color */
    public void setColor(Color color) {
        this.color = color;
        updateText();
    }
    
    /* set new text */
    public void setText(String text) {
        this.text = text;
        updateText();
    }
}