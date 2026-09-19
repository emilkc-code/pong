import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Shop extends World
{
    public static final Product[] products = { new CounterStrike(), new LeagueOfLegends(), new Minecraft(), new RocketLeague(), new TeamFortress() };
    private List<Button> buyButtons = new ArrayList<>();;
    
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
        addObject(menuButton, getWidth() / 2, getHeight() - 80);
        
        drawMoney();
    }
    
    private void drawMoney() {
        if (moneyText != null) { removeObject(moneyText); }
        moneyText = new Text("Money: " + Integer.toString(GameManager.getMoney()), 200, 50, Fonts.getNormal());
        addObject(moneyText, getWidth() / 2, getHeight() - 150);
    }
    
    public void tryBuy(int n, Button button) {
        if (products[n].isOwned()) {
            GameManager.setSkin(n);
            updateButtons(n);
            return;
        }
        
        if (GameManager.getMoney() < products[n].getPrice()) { return; }
        
        GameManager.setMoney(GameManager.getMoney() - products[n].getPrice());
        drawMoney();
        
        products[n].setOwned(true);
        GameManager.setSkin(n);
        updateButtons(n);
    }
    
    private void updateButtons(int n) {
        for (int i = 0; i < buyButtons.size(); i++) {
            if (!products[i].isOwned()) {
                buyButtons.get(i).setCanBuy(GameManager.getMoney() >= products[i].getPrice());
                buyButtons.get(i).drawNew(Integer.toString(products[i].getPrice()));
                continue;
            }
            
            if (i == n) { buyButtons.get(i).drawNew("Equipped"); continue; }
            buyButtons.get(i).drawNew("Owned");
        }
    }
    
    private void setupProducts() {
        background.setColor(Colors.getText());
        
        for (int i = 0; i < products.length; i++) {
            GreenfootImage image = new GreenfootImage(products[i].images.getProduct());
            
            int width = getWidth() / 4;
            int height = getWidth() / 4;
            image.scale(width, height);
            
            int x = (int) (getWidth() / 4 * (i % 4));
            int y = (int) (getWidth() / 4 * ((int) (i / 4) * 2 + 1) - width / 2);
            y += 20;
            background.drawImage(image, x, y);
            
            x += width / 2;
            int buttonWidth = (int) (width * 0.95f);
            int buttonHeight = height / 4;
            y += height + 10 + buttonHeight / 2;
            Button button = new Button("", buttonWidth, buttonHeight);
            addObject(button, x, y);
            buyButtons.add(button);
            button.setProductIndex(i);
            if (GameManager.getSkin() == i) { button.drawNew("Equipped"); }
            else                            { button.drawNew("Owned"); }
            
            if (products[i].isOwned()) { continue; }
            
            button.setCanBuy(GameManager.getMoney() >= products[i].getPrice());
            button.drawNew(Integer.toString(products[i].getPrice()));
        }
    }
}