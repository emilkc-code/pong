import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Background extends Actor
{
    public Background(int width, int height, GreenfootImage img, GreenfootSound sound) {
        img.scale(width, height);
        setImage(img);  
        GreenfootSound backgroundS = sound;
        backgroundS.playLoop();
    }
}
