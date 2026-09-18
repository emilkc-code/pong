import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class SaveManager  
{
    public void saveHighScore(int score) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"));
            writer.write("Highscore = " + score);
            writer.close();
            //System.out.println("High score saved!");
        } catch (IOException e) {
            System.out.println("Error saving high score.");
            e.printStackTrace();
        }
    }
    
    public int loadHighScore() {
        int currentHighScore = 0; // Default fallback if the file doesn't exist yet
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"));
            String line = reader.readLine();
            
            if (line != null && line.contains("=")) {
                // Splits "Highscore = 150" into ["Highscore ", " 150"]
                String[] parts = line.split("=");
                // Takes the second part, trims extra spaces, and converts to int
                currentHighScore = Integer.parseInt(parts[1].trim());
            }
            
            reader.close();
        } catch (IOException | NumberFormatException e) {
            //System.out.println("No valid high score file found. Starting at 0.");
        }
        
        return currentHighScore;
    }
    
    
    public void saveGameData(String dataToWrite) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savegame.txt"));
            writer.write(dataToWrite);
            writer.close();
            //System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
    
    public void readGameData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"));
            String line = reader.readLine();
            
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file (it might not exist yet).");
            e.printStackTrace();
        }
    }
}
