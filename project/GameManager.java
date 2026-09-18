import greenfoot.*;
public class GameManager  
{
    private static int windowWidth = 500;
    private static int windowHeight = 700;
    private static int wins = 0;
    private static int loses = 0;
    private static int money = 150;
    private static int highscore;
    
    public static void setWindowWidth(int n) { windowWidth = n; }
    public static void setWindowHeight(int n) { windowHeight = n; }
    public static void setWins(int n) { wins = n; }
    public static void setLoses(int n) { loses = n; }
    public static void setMoney(int n) { money = n; }
    public static void setHighscore(int n) { highscore = n; }
    
    public static int getWindowWidth() { return windowWidth; }
    public static int getWindowHeight() { return windowHeight; }
    public static int getWins() { return wins; }
    public static int getLoses() { return loses; }
    public static int getMoney() { return money; }
    public static int getHighscore() { return highscore; }
    
    public GameManager() {
        highscore = new SaveManager().loadHighScore();
    }
}