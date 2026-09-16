import greenfoot.*;

public class TextLabel extends Actor {
    public TextLabel(String text, int fontSize) {
        updateText(text, fontSize);
    }
    
    public void updateText(String text, int fontSize) {
        int width = text.length() * (fontSize / 2) * 2;
        int height = fontSize + 10;
        
        GreenfootImage img = new GreenfootImage(width, height);
        Font font = new Font(true, false, fontSize);
        img.setFont(font);
        img.setColor(Color.WHITE);
        
        img.drawString(text, width / 4, fontSize);
        
        setImage(img);
    }
        public void setBlackColor(String text, int fontSize) {
        int width = text.length() * (fontSize / 2) * 2;
        int height = fontSize + 10;
        GreenfootImage img = new GreenfootImage(width, height);
        Font font = new Font(true, false, fontSize);
        img.setFont(font);
        img.setColor(Color.BLACK);
        
        img.drawString(text, width / 4, fontSize);
        
        setImage(img);
        
    }
}