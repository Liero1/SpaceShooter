import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Enemy {

    private final String pathToEnemyShipImg = "src/main/resources/SmallEnemy.png";

    private int x;
    private int y;
    private int w;
    private int h;
    private int distance;
    private int health;
    private Image shipImage;
    Boolean isVisible;

    public Enemy(int x, int y, int h, int distance)
    {
        setX(x);
        setY(y);
        setHealth(h);
        loadImage();
        setDistance(distance);
        isVisible = true;

    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
    public void move()
    {
        y += distance;
        if(y==600)
            isVisible = false;
    }
    public Boolean getVisible() {
        return isVisible;
    }
    public void subtractHealth(int i)
    {
        this.health = health - i;
    }
    public void setVisible(Boolean visible) {
        isVisible = visible;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Image getShipImage() {
        return shipImage;
    }

    public void setShipImage(Image shipImage) {
        this.shipImage = shipImage;
    }

    private void loadImage()
    {
        ImageIcon img = new ImageIcon(pathToEnemyShipImg);
        shipImage = img.getImage();

        w = shipImage.getWidth(null);
        h = shipImage.getHeight(null);


    }


}
