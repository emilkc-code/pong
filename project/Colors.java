import greenfoot.*;

public class Colors extends Style
{
    private static final Color text                  = new Color(220, 220, 220);
    private static final Color background            = new Color(35, 35, 35);
    private static final java.awt.Color buttonBorder = new java.awt.Color(150, 150, 150);
    private static final Color canBuyButton          = new Color(35, 150, 35);
    private static final Color cantBuyButton         = new Color(150, 35, 35);
    private static final Color ownedButton           = new Color(35, 35, 150);
    
    public static Color getText()                  { return text; }
    public static Color getBackground()            { return background; }
    public static java.awt.Color getButtonBorder() { return buttonBorder; }
    public static Color getCanBuyButton()          { return canBuyButton; }
    public static Color getCantBuyButton()         { return cantBuyButton; }
    public static Color getOwnedButton()           { return ownedButton; }
}