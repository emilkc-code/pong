import greenfoot.*;

public class IntroWorld extends World
{
    
    public IntroWorld() {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        Greenfoot.setSpeed(58);
        SaveManager.loadData();
        SoundManager.playAmbient();
        
        GreenfootImage background = getBackground();
        background.setColor(Colors.getBackground());
        background.fillRect(0, 0, getWidth(), getHeight());
        
        Product skin = Shop.products[GameManager.getSkin()];
        GreenfootImage image = new GreenfootImage(skin.images.getBackground());
        image.scale(getWidth(), getHeight());
        background.drawImage(image, 0, 0);
        
        
        Text title = new Text(120, 60);
        title.setText("Ping");
        title.setFont(Fonts.getTitle());
        title.setIsFilled(true);
        addObject(title, getWidth() / 2, 150);
        
        Text highscore = new Text(160, 30);
        highscore.setText("Highscore: " + Integer.toString(new GameManager().getHighscore()));
        highscore.setIsFilled(true);
        addObject(highscore, getWidth() / 2, 200);
        
        
        int x = 100;
        int y = 60;
        
        Button playButton = new Button(x, y);
        playButton.setWorld("PingWorld");
        
        Text playText = new Text(x, y);
        playText.setText("Play");
        //playText.setIsFilled(true);
        playText.setHasBorder(true);
        
        addObject(playText,    getWidth() / 2, getHeight() - 250);
        addObject(playButton,  getWidth() / 2, getHeight() - 250);
        
        
        Button watchButton = new Button(x, y);
        watchButton.setWorld("PingWorldAI");
        
        Text watchText = new Text(x, y);
        watchText.setText("Watch");
        //watchText.setIsFilled(true);
        watchText.setHasBorder(true);
        
        addObject(watchText,   getWidth() / 2, getHeight() - 180);
        addObject(watchButton, getWidth() / 2, getHeight() - 180);
        
        
        Button shopButton  = new Button(x, y);
        shopButton.setWorld("Shop");
        
        Text shopText = new Text(x, y);
        shopText.setText("Shop");
        //shopText.setIsFilled(true);
        shopText.setHasBorder(true);
        
        addObject(shopText,    getWidth() / 2, getHeight() - 50);
        addObject(shopButton,  getWidth() / 2, getHeight() - 50);
    }
}