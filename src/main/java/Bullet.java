import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Bullet {
    private final int BULLET_SPEED = 3;
    int x;
    int y;
    int w;
    int h;
    int damage;
    Image image;
    Boolean isVisible;
    public void setVisible(Boolean b)
    {
        this.isVisible = b;
    }
    public int getDmg()
    {
        return this.damage;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public Image getImage()
    {
        return this.image;
    }
    public Boolean isVisible()
    {
        return this.isVisible;
    }
    public Bullet(int x, int y, int dmg, ImageIcon img)
    {
        this.damage = dmg;
        this.x = x;
        this.y = y;
        image = img.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);

        isVisible = true;
    }
    public void move()
    {
        y -= BULLET_SPEED;
        if(abs(y)> Board.WINDOW_H)
            isVisible = false;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

}
