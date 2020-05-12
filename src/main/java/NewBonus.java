import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

//Klasa reprezntujaca graficznie Bonus pojawiajacy sie na planszy
//Nie ma nic wspolnego ze wzorcem dekorator ktory opakowuje obiekt gracza w nowe umiejetnosci
//Po kolizji NewBonus z GamePlayer w pliku Board (możliwa nowa nazwa pliku w przyszłości: Game)
//obiekt GamePlayer zostaje opakowany w Dekorator na podstawie Randomowej liczby
public class NewBonus {
    private int x;
    private int y;
    private int distance = 2;
    Boolean isVisible = true;
    Image image;
    int w;
    int h;

    public NewBonus(int x, int y)
    {
        this.x = x;
        this.y = y;
        ImageIcon img = new ImageIcon("src/main/resources/Gift.png");
        image = img.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);

    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }


    public void move()
    {
        y += distance;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }



}
