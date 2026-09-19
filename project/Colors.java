import greenfoot.*;

public class Colors extends Style
{
    private static final Color background          = new Color(35, 35, 35);
    private static final Color text                = new Color(220, 220, 220);
    private static final Color textFill            = new Color(100, 100, 100);
    private static final java.awt.Color textBorder = new java.awt.Color(150, 150, 150);
    private static final Color productCanBuy       = new Color(35, 150, 35);
    private static final Color productCantBuy      = new Color(150, 35, 35);
    private static final Color productOwned        = new Color(35, 35, 100);
    private static final Color productEquipped     = new Color(35, 150, 150);
    
    public static Color getBackground()          { return background; }
    public static Color getText()                { return text; }
    public static Color getTextFill()            { return textFill; }
    public static java.awt.Color getTextBorder() { return textBorder; }
    public static Color getProductCanBuy()       { return productCanBuy; }
    public static Color getProductCantBuy()      { return productCantBuy; }
    public static Color getProductOwned()        { return productOwned; }
    public static Color getProductEquipped()     { return productEquipped; }
}