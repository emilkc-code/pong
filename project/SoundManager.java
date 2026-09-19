import greenfoot.*;
public class SoundManager extends Actor
{
    private static Product skin = Shop.products[GameManager.getSkin()];
    
    private static GreenfootSound ambient = skin.sounds.getAmbient();
    private static GreenfootSound loss = skin.sounds.getLoss();
    private static GreenfootSound paddleHit = skin.sounds.getPaddleHit();
    private static GreenfootSound wallHit = skin.sounds.getWallHit();
    private static GreenfootSound win = skin.sounds.getWin();
    
    static {
        ambient.setVolume(100);
        loss.setVolume(40);
        paddleHit.setVolume(40);
        wallHit.setVolume(40);
        win.setVolume(40);
    }
    
    public static void updateSounds() {
        skin = Shop.products[GameManager.getSkin()];
        
        ambient.stop();
        loss.stop();
        paddleHit.stop();
        wallHit.stop();
        win.stop();
        
        ambient = skin.sounds.getAmbient();
        loss = skin.sounds.getLoss();
        paddleHit = skin.sounds.getPaddleHit();
        wallHit = skin.sounds.getWallHit();
        win = skin.sounds.getWin();
    }
    
    public static void playAmbient() {
        if (ambient.isPlaying() && skin == Shop.products[GameManager.getSkin()]) { return; }
        ambient.stop();
        ambient.playLoop();
    }
    
    public static void playLoss() {
        loss.stop();
        loss.play();
    }
    
    public static void playPaddleHit() {
        paddleHit.stop();
        paddleHit.play();
    }
    
    public static void playWallHit() {
        wallHit.stop();
        wallHit.play();
    }
    
    public static void playWin() {
        win.stop();
        win.play();
    }
}