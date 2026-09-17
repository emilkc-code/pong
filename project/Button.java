import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Button extends Actor
{
    /* Instance variables */
    private GreenfootImage image = new GreenfootImage(50,50);
    private int price;
    private boolean purchased;
    private int buttonWidth;
    private int buttonHeight;
    private Product product;
    private GameManager gm = new GameManager();
    private Color col;    
    
    /**
     * Constructor for button
     * @param cost
     * @param prePurchased
     * @param width
     * @param height
     * @param prod
     */
    public Button(int cost, boolean prePurchased, int width, int height, Product prod )
    {
        this.price = cost;
        this.purchased = prePurchased;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.product = prod;

        /* set image */
        setColor(new Color(35, 220, 35));

        /* If purchased, can buy or can't buy color settings */
        if (purchased) {
            setColor(new Color(35, 35, 220));
        }
        else if (cost <= gm.getMoney()){
            setColor(new Color(35, 220, 35));
        }
        else if (cost > gm.getMoney()){
            setColor(new Color(220, 35, 35));
        }
    }
    
    /* Checking if said button has been clicked and updated money, shop and applies new skin pack */
    public void act()
    {
        if (Greenfoot.mouseClicked(this) && !purchased && price <= gm.getMoney() && product != null){
            setColor(Color.BLUE);
            purchased = true;
            gm.setMoney(gm.getMoney() - price);
            gm.setActiveSkinPack(product);
            Shop shop_menu = (Shop) getWorld();
            shop_menu.updateAll();
        }
    }
    
    /**
     * Setting color of said button
     */
    public void setColor(Color color)
    {
        col = color;
        image.setColor(color);
        image.scale(buttonWidth, buttonHeight);
        image.fill();
        setImage(image);
    }
    
    /**
     * Getting current color
     */
    public Color getColor() {
        return col;
    }
    
    /**
     * Getting current cost
     */
    public int getCost() {
        return price;
    }
    
    /**
     * Getting if purchased or not
     */
    public boolean getPurchased() {
        return purchased;
    }
}
