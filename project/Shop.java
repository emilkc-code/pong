import greenfoot.*;
import java.util.List;

public class Shop extends World
{
    private Product[] products = { new CounterStrike(), new LeagueOfLegends(), new RocketLeague() };
    
    private Text moneyText;
    
    GreenfootImage background = getBackground();
    
    public Shop()
    {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        emptySetup();
        setupProducts();
    }
    
    private void emptySetup() {
        background.setColor(Colors.getBackground());
        background.fillRect(0, 0, getWidth(), getHeight());
        
        Text shop_text = new Text("Shop", 150, 70, Fonts.getTitle());
        addObject(shop_text, 70, 40);
        
        Button menuButton = new Button("Return To Main Menu", 350, 70);
        menuButton.setWorld("IntroWorld");
        addObject(menuButton, 250, getHeight() - 80);
        
        drawMoney();
    }
    
    public void drawMoney() {
        if (moneyText != null) { removeObject(moneyText); }
        moneyText = new Text("Money: " + Integer.toString(GameManager.getMoney()), 200, 50, Fonts.getNormal());
        addObject(moneyText, getWidth() / 2, getHeight() - 150);
    }
    
    private void setupProducts() {
        background.setColor(Colors.getText());
        
        for (int i = 0; i < products.length; i++) {
            GreenfootImage image = new GreenfootImage(products[i].images.getProduct());
            
            int width = getWidth() / 4;
            int height = getWidth() / 4;
            image.scale(width, height);
            
            int x = (int) (getWidth() / 4 * (i % 2 * 2 + 1) - getWidth() / 8);
            int y = (int) (getWidth() / 4 * ((int) (i / 2) % 2 * 2 + 1) - getWidth() / 8);
            y += 20;
            background.drawImage(image, x, y);
            
            x += width / 2;
            int buttonHeight = height / 4;
            y += height + 10 + buttonHeight / 2;
            Button button = new Button("", width, buttonHeight);
            addObject(button, x, y);
            button.setPrice(products[i].getPrice());
        }
    }
}