import greenfoot.*;

public class CounterStrike extends Product
{
    private String name = "counter-strike";
    
    public CounterStrike()
    {
        price = 800;
        owned = true;
        
        images.product = new GreenfootImage(name + "_product.png");
        
        images.background = new GreenfootImage(name + "_background.png");
        images.ball = new GreenfootImage(name + "_ball.png");
        images.bottomPaddle = new GreenfootImage(name + "_bottom-paddle.png");
        images.topPaddle = new GreenfootImage(name + "_top-paddle.png");
        
        sounds.ambient = new GreenfootSound(name + "_ambient.mp3");
        sounds.loss = new GreenfootSound(name + "_loss.mp3");
        sounds.paddleHit = new GreenfootSound(name + "_paddle-hit.mp3");
        sounds.wallHit = new GreenfootSound(name + "_wall-hit.mp3");
        sounds.win = new GreenfootSound(name + "_win.mp3");
    }
}