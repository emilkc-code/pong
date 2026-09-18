import greenfoot.*;

public class Text extends Actor
{
    public Text(String text, int width, int height, Font font) {
        GreenfootImage image = new GreenfootImage(width, height);
        image.clear();
        TextHelper.drawCenteredString(image, text, font, Colors.getText());
        setImage(image);
    }
}