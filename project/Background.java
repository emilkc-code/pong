import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Background extends Actor
{
    /* Background Sound instance variable */
    private GreenfootSound backgroundS;
    
    /* Constructor for background */
    public Background(int width, int height, GreenfootImage img, GreenfootSound sound) {
        img.scale(width, height);
        setImage(img);  
        backgroundS = sound;
        backgroundS.playLoop();
    }
    
    /**
     * Stopping background "music" to avoid uneeded sounds in other menus/worlds
     */
    public void stopBGSound() {
        backgroundS.stop();
    }
}
