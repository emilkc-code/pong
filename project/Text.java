import greenfoot.*;

public class Text extends UI
{
    private String text                = "Sample Text";
    private Font font                  = Fonts.getNormal();
    private Color textColor            = Colors.getText();
    private boolean isFilled           = false;
    private Color fillColor            = Colors.getTextFill();
    private boolean hasBorder          = false;
    private int borderThickness        = 5;
    private java.awt.Color borderColor = Colors.getTextBorder();
    
    public void setText(String text)                 { this.text = text;    drawNew(); }
    public void setFont(Font font)                   { this.font = font;    drawNew(); }
    public void setTextColor(Color color)            { textColor = color;   drawNew();}
    public void setIsFilled(boolean b)               { isFilled = b;        drawNew();}
    public void setFillColor(Color color)            { fillColor = color;   drawNew();}
    public void setHasBorder(boolean b)              { hasBorder = b;       drawNew();}
    public void setBorderThickness(int n)            { borderThickness = n; drawNew();}
    public void setBorderColor(java.awt.Color color) { borderColor = color; drawNew();}
    
    private GreenfootImage image;
    
    public Text(int width, int height) {
        image = new GreenfootImage(width, height);
        setImage(image);
    }
    
    private void drawNew() {
        image.clear();
        
        if (isFilled) {
            image.setColor(fillColor);
            image.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        
        // Text is drawn through Graphics2D (see TextHelper)
        TextHelper.drawCenteredString(image, text, font, textColor);
        if (hasBorder) { TextHelper.drawThickRect(image, borderThickness, borderColor); }
    }
}