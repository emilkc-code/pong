public class GameManager  
{
    private static int wins = 0;
    private static int loses = 0;
    private static int money = 0;
    private static int level = 0;
    
    public void setWins(int n) { wins = n; }
    public void setLoses(int n) { loses = n; }
    public void setMoney(int n) { money = n; }
    public void setLevel(int n) { level = n; }
    
    public int getWins() { return wins; }
    public int getLoses() { return loses; }
    public int getMoney() { return money; }
    public int getLevel() { return level; }
}