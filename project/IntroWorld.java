import greenfoot.*;

public class IntroWorld extends World
{
    private Text title     = new Text("Ping",                                                             120, 90, Fonts.getTitle());
    private Text highscore = new Text("Highscore: " + Integer.toString(new GameManager().getHighscore()), 230, 50, Fonts.getNormal());
    
    private Button playButton  = new Button("Play",  100, 60);
    private Button watchButton = new Button("Watch", 100, 60);
    private Button shopButton  = new Button("Shop",  100, 60);
    
    public IntroWorld() {
        super(GameManager.getWindowWidth(), GameManager.getWindowHeight(), 1);
        
        playButton.setWorld("PingWorld");
        watchButton.setWorld("PingWorldAI");
        shopButton.setWorld("Shop");
        
        GreenfootImage background = getBackground();
        background.setColor(new Color(35, 35, 35));
        background.fillRect(0, 0, getWidth(), getHeight());
        
        addObject(title,        getWidth() / 2, 150);
        addObject(highscore,    getWidth() / 2, 200);
        addObject(playButton,  getWidth() / 2, getHeight() - 250);
        addObject(watchButton, getWidth() / 2, getHeight() - 180);
        addObject(shopButton,  getWidth() / 2, getHeight() - 50);
    }
}