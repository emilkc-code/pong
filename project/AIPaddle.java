import greenfoot.*;

public class AIPaddle extends Actor
{
    private int width;
    private int height;
    private Double targetPoint;
    
    private int speed;
    private Ball ball;
    
    public AIPaddle(int width, int height, int speed) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        createImage();
        
    }
    
    public void act() {
        if (ball == null || targetPoint == null) { return; }
        moveHere((int) Math.round(targetPoint));
    }
    
    public void setBall(Ball ball) {
        this.ball = ball;
    }
    
    public Ball getBall() {
        return ball;
    }
    
    private void moveHere(int n) {
        if (this.getX() < n
         && this.getX() < getWorld().getWidth() - width / 2) {
             this.setLocation(getX() + speed, getY());
        }
        
        if (this.getX() > n
         && this.getX() > width / 2) {
             this.setLocation(getX() - speed, getY());
        }
    }
    
    public void setupPredictor(Ball ball) {
        double width = getWorld().getWidth() - ball.getSize();
        double height = getWorld().getHeight() - 140 - ball.getSize();
        double startPosition = ball.getX() / width;
        double predictionPoint = height * 1.1 / width;
        
        targetPoint = predict(ball.getRotation() - 90, startPosition, predictionPoint);
        targetPoint = (1 - targetPoint) * width;
    }
    
    private double predict(double angle, double startPosition, double predictionPoint) {
        angle = Math.toRadians(angle);
        double a = Math.sin(-angle) / Math.cos(-angle);
        double count = a * predictionPoint + 1 - startPosition;
        double flipper = Math.pow(-1, Math.floor(count));
        double result = trueModulo(count, 1) * flipper + 0.5 + 0.5 * (-1) * flipper;
        return result;
    }
    
    private double trueModulo(double a, double b) {
    return a - b * Math.floor(a / b);
}
    
    private void createImage() {
        GreenfootImage img = new GreenfootImage("ak.png");
        img.scale(this.width, this.height);
        setImage(img);
    }

}
