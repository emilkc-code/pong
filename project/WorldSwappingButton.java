import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WorldSwappingButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WorldSwappingButton extends Actor
{
    private int buttonWidth;
    private int buttonHeight;
    private String targetWorldName;
    
    public WorldSwappingButton(String targetWorldName, int width, int height)
    {
        this.targetWorldName = targetWorldName;
        this.buttonWidth = width;
        this.buttonHeight = height;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            switch (targetWorldName) {
                case "IntroWorld":
                    Greenfoot.setWorld(new IntroWorld());
                    break;
                case "Shop":
                    Greenfoot.setWorld(new Shop());
                    break;
                case "Ping":
                    Greenfoot.setWorld(new PingWorld(true));
            }
        }
    }
    
    public void setTextandColor(String text, int x , int y) {
        GreenfootImage image = new GreenfootImage(buttonWidth, buttonHeight);
        image.setColor(Color.BLACK);
        image.fill();
        image.setFont( new Font("SansSerif", true, false, 28));
        image.setColor(Color.WHITE);
        if (x == 0 || y == 0) { image.drawString(text, buttonWidth /2, buttonHeight /2); }
        else { image.drawString(text, x, y); }
        //image.scale(buttonWidth, buttonHeight);
        setImage(image);
    }
}
