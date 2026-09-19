import greenfoot.*;

public class Fonts extends Style 
{
    private static final Font smallText  = new greenfoot.Font("Arial", true, false, 14);
    private static final Font normalText = new greenfoot.Font("Arial", true, false, 24);
    private static final Font titleText  = new greenfoot.Font("Arial", true, false, 48);
    
    public static Font getSmall()  { return smallText; }
    public static Font getNormal() { return normalText; }
    public static Font getTitle()  { return titleText; }
}