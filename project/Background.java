import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Background extends Actor
{
    private GreenfootSound backgroundS;
    public Background(int width, int height, GreenfootImage img, GreenfootSound sound) {
        img.scale(width, height);
        setImage(img);  
        backgroundS = sound;
        backgroundS.playLoop();
    }
    
    public void stopBGSound() {
        backgroundS.stop();
    }
}
