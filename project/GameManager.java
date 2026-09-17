import greenfoot.*;
public class GameManager  
{
    // Static instance variables for storing while program is open
    private static int wins = 0;
    private static int loses = 0;
    private static int money;
    private static int level = 0;
    private static int highscore;
    private static Product activeSkinPack;
    private static int initializations = 0;
    
    //setters and getters for each stat
    public void setWins(int n) { wins = n; }
    public void setLoses(int n) { loses = n; }
    public void setMoney(int n) { money = n; }
    public void setLevel(int n) { level = n; }
    public void setHighscore(int n) { highscore = n; }
    public void setActiveSkinPack(Product pack) { activeSkinPack = pack; }
    
    public int getWins() { return wins; }
    public int getLoses() { return loses; }
    public int getMoney() { return money; }
    public int getLevel() { return level; }
    public int getHighscore() { return highscore; }
    public Product getActiveSkinPack() { return activeSkinPack; };
    
    // Constructor for creation
public GameManager() {
        initializations++;
        // If it's the first time GameManager loads, set csgo pack as default
        if (initializations == 1) {
            activeSkinPack = new Product(
                "CSGO", 
                150,
                new GreenfootImage("ct.png"), 
                new GreenfootImage("ak.png"), 
                new GreenfootImage("mir.jpg"), 
                new GreenfootImage("c4.png"), 
                new GreenfootSound("WallThudSound.mp3"), 
                new GreenfootSound("BombBeep.mp3"), 
                new GreenfootSound("WinningRobloxOldWinningSoundEffect.mp3"), 
                new GreenfootSound("WinningRobloxOldWinningSoundEffect.mp3"),
                new GreenfootSound("cs2LobbyMusic.mp3")
            );
        }
    }
}