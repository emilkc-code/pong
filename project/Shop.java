import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Shop extends World
{
    public static final Product[] products = {
        new CounterStrike(),
        new LeagueOfLegends(),
        new MinecraftOverworld(),
        new MinecraftNether(),
        new RocketLeague(),
        new TeamFortress(),
        new Roblox()
    };
    
    private List<Text> productButtonTexts = new ArrayList<>();;
    
    private Text moneyText = new Text(200, 50);
    
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
        
        Text shopText = new Text(150, 70);
        shopText.setText("Shop");
        shopText.setFont(Fonts.getTitle());
        addObject(shopText, 70, 40);
        
        
        int x = 350;
        int y = 70;
        
        Button menuButton = new Button(350, 70);
        menuButton.setWorld("IntroWorld");
        
        Text menuText = new Text(x, y);
        menuText.setText("Return To Main Menu");
        //menuText.setIsFilled(true);
        menuText.setHasBorder(true);
        
        addObject(menuText,   getWidth() / 2, getHeight() - 80);
        addObject(menuButton, getWidth() / 2, getHeight() - 80);
        
        
        addObject(moneyText, getWidth() / 2, getHeight() - 150);
        updateMoneyText();
    }
    
    private void updateMoneyText() { moneyText.setText("Money: " + Integer.toString(GameManager.getMoney())); }
    
    public void tryBuy(int n, Button button) {
        if (products[n].isOwned()) {
            GameManager.setSkin(n);
            SaveManager.saveData();
            SoundManager.updateSounds();
            SoundManager.playAmbient();
            updateButtonTexts(n);
            return;
        }
        
        if (GameManager.getMoney() < products[n].getPrice()) { return; }
        
        GameManager.setMoney(GameManager.getMoney() - products[n].getPrice());
        updateMoneyText();
        
        products[n].setOwned(true);
        GameManager.setSkin(n);
        SaveManager.saveData();
        SoundManager.updateSounds();
        SoundManager.playAmbient();
        updateButtonTexts(n);
    }
    
    private void updateButtonTexts(int n) {
        for (int i = 0; i < productButtonTexts.size(); i++) {
            String text = "Owned";
            Color color = Colors.getProductOwned();
            if (!products[i].isOwned()) {
                text = Integer.toString(products[i].getPrice());
                color = Colors.getProductCanBuy();
                if (GameManager.getMoney() < products[i].getPrice()) { color = Colors.getProductCantBuy(); }
            }
            
            else if (i == n) {
                text = "Equipped";
                color = Colors.getProductEquipped();
            }
            
            productButtonTexts.get(i).setText(text);
            productButtonTexts.get(i).setFillColor(color);
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
            int y = (int) (getWidth() / 5 * ((int) (i / 4) * 2 + 1) - width / 2);
            y += 60;
            background.drawImage(image, x, y);
            
            x += width / 2;
            int buttonWidth = (int) (width * 0.95f);
            int buttonHeight = height / 4;
            y += height + 10 + buttonHeight / 2;
            Button button =   new Button(buttonWidth, buttonHeight);
            Text buttonText = new Text(  buttonWidth, buttonHeight);
            buttonText.setIsFilled(true);
            addObject(buttonText, x, y);
            addObject(button,     x, y);
            productButtonTexts.add(buttonText);
            button.setProductIndex(i);
            
            String text = "Owned";
            Color color = Colors.getProductOwned();
            
            if (!products[i].isOwned()) {
                text = Integer.toString(products[i].getPrice());
                color = Colors.getProductCanBuy();
                if (GameManager.getMoney() < products[i].getPrice()) { color = Colors.getProductCantBuy(); }
            }
            
            if (GameManager.getSkin() == i) {
                text = "Equipped";
                color = Colors.getProductEquipped();
            }
            
            buttonText.setText(text);
            buttonText.setFillColor(color);
        }
    }
}