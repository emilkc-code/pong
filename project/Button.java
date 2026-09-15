import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootImage image = new GreenfootImage(50,50);
    private int price;
    private boolean purchased;
    private int buttonWidth;
    private int buttonHeight;
    private Product product;
    private GameManager gm = new GameManager();
    
    public Button(int cost, boolean prePurchased, int width, int height, Product prod )
    {
        this.price = cost;
        this.purchased = prePurchased;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.product = prod;
        
        //set image

        setColor(Color.GREEN);
        
        if (purchased) {
            setColor(Color.BLUE);
        }
        else if (cost <= gm.getMoney()){
            setColor(Color.GREEN);
        }
        else if (cost > gm.getMoney()){
            setColor(Color.RED);
        }
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this) && !purchased && price <= gm.getMoney() && product != null){
            setColor(Color.BLUE);
            gm.setMoney(gm.getMoney() - price);
            gm.setActiveSkinPack(product);
        }
    }
    
    public void setColor(Color color)
    {
        image.setColor(color);
        image.scale(buttonWidth, buttonHeight);
        image.fill();
        setImage(image);
    }
}
