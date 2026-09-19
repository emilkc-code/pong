import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SaveManager extends Manager
{
    public static void saveData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savedata.txt"));
            writer.write("Highscore = " + GameManager.getHighscore());
            writer.newLine();
            writer.write("Money = " + GameManager.getMoney());
            writer.newLine();
            writer.write("Wins = " + GameManager.getWins());
            writer.newLine();
            writer.write("Loses = " + GameManager.getLoses());
            writer.newLine();
            List<Integer> skins = new ArrayList();            
            for (int i = 0; i < Shop.products.length; i++) {
                if (!Shop.products[i].isOwned()) {continue;}
                skins.add(i);
            }
            writer.write("Skins = " + skins);
            writer.newLine();
            writer.write("Skin = " + GameManager.getSkin());
            writer.close();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data.");
            e.printStackTrace();
        }
    }
    
    public static void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("savedata.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=");
                    String key = parts[0].trim();
                    String dataValue = parts[1].trim();
                    
                    switch (key) {
                        case "Highscore": 
                            GameManager.setHighscore(Integer.parseInt(dataValue));
                            break;
                            
                        case "Money": 
                            GameManager.setMoney(Integer.parseInt(dataValue));
                            break;
                            
                        case "Wins": 
                            GameManager.setWins(Integer.parseInt(dataValue));
                            break;
                            
                        case "Loses": 
                            GameManager.setLoses(Integer.parseInt(dataValue));
                            break;
                            
                        case "Skin": 
                            GameManager.setSkin(Integer.parseInt(dataValue));
                            break;
                            
                        case "Skins":
                            String skinData = dataValue.replace("[", "").replace("]", "");
                            
                            if (!skinData.isEmpty()) {
                                String[] skinIds = skinData.split(",");
                                for (String idStr : skinIds) {
                                    int skinIndex = Integer.parseInt(idStr.trim());
                                    Shop.products[skinIndex].setOwned(true);
                                }
                            }
                            break;
                    }
                }
            }
            reader.close();
            System.out.println("Data loaded successfully!");
            
        } catch (IOException | NumberFormatException e) {
            System.out.println("No save file found yet or error reading data. Starting fresh.");
            e.printStackTrace(); // Optional: helps see if anything else goes wrong
        }
    }
}