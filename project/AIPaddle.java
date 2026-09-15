import greenfoot.*;

public class AIPaddle extends Actor
{
    private int width;
    private int height;
    private double targetPoint;
    private GreenfootImage image;
    private int speed;
    private Ball ball;
    
    public AIPaddle(int width, int height, int speed, GreenfootImage img) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.image = img;
        createImage();
        
    }
    
    public void act() {
        if (ball == null || targetPoint < 0) { return; }
        moveHere((int) targetPoint);
    }
    
    public void setBall(Ball ball) { this.ball = ball; }
    
    public Ball getBall() { return ball; }
    
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
    
    public void resetToCenter() {
        targetPoint = getWorld().getWidth() / 2;
    }
    
    public void setupPredictor(Ball ball) {
        double width = getWorld().getWidth() - ball.getSize();
        double height = getWorld().getHeight() - 60 - ball.getSize() / 2 - (getWorld().getHeight() - ball.getY());
        double startPosition = ball.getX() / width;
        double predictionPoint = height / width;
        
        targetPoint = predict(ball.getRotation() - 90, startPosition, predictionPoint);
        targetPoint = (1 - targetPoint) * width + ball.getSize() / 2;
        targetPoint = Math.round(targetPoint);
        
        double snipe = ball.getX() - getWorld().getWidth() / 2;
        snipe /= width / 2;
        targetPoint += snipe * 10;
    }
    
    private double predict(double angle, double startPosition, double predictionPoint) {
        angle = Math.toRadians(angle);
        double a = Math.sin(-angle) / Math.cos(-angle);
        double count = a * predictionPoint + 1 - startPosition;
        double flipper = Math.pow(-1, Math.floor(count));
        double result = trueModulo(count, 1) * flipper + 0.5 + 0.5 * (-1) * flipper;
        return result;
    }
    
    private double trueModulo(double a, double b) { return a - b * Math.floor(a / b); }
    
    private void createImage() {
        image.scale(this.width, this.height);
        setImage(image);
    }

}
