import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Background extends Actor
{
    public Background(int width, int height) {
        GreenfootImage img = new GreenfootImage("mir.jpg");
        img.scale(width, height);
        setImage(img);  
        GreenfootSound backgroundS = new GreenfootSound("cs2LobbyMusic.mp3");
        backgroundS.playLoop();
    }
}
