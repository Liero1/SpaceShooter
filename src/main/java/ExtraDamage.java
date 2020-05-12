import javax.swing.*;
import java.awt.*;

public class ExtraDamage extends Bonus {

    //Dekorator ktory podwyzsza zadawane obrazenia
    //oraz zmienia teksture pocisku
    public ExtraDamage(IPlayer player)
    {
        super(player);
        setBigDamageBulletTexture();
        addDamage();

    }
    private void setBigDamageBulletTexture()
    {
        ImageIcon img = new ImageIcon(pathToExtraDmgBulletTypeImg);
        setBulletTypeImg(img);
    }
    private void addDamage()
    {
        setDamage(3);
    }
    public void setSpaceShipImage(String s)
    {
        player.setSpaceShipImage(s);
    }
    public void setBulletTypeImg(ImageIcon img)
    {
        player.setBulletTypeImg(img);
    }
    public int getDistance()
    {
        return player.getDistance();
    }
    public int getDamage()
    {
        return player.getDamage();
    }
    public int getX()
    {
        return player.getX();
    }
    public int getY()
    {
        return player.getY();
    }
    public void setDamage(int d)
    {
        player.setDamage(d);
    }

    public void setDistance(int d)
    {
        player.setDistance(d);
    }
    public void setX(int x)
    {
        player.setX(x);
    }
    public void setY(int y)
    {
        player.setY(y);
    }
    public void moveLeft()
    {
        player.moveLeft();
    }
    public void moveRight()
    {
        player.moveRight();
    }
    public void stopMoving()
    {
        player.stopMoving();
    }
    public void fire()
    {
        player.fire();
    }
    public Image getImage()
    {
        return player.getImage();
    }
    public void move()
    {
        player.move();
    }
    public int getWidth()
    {
        return player.getWidth();
    }
    public int getHeight()
    {
        return player.getHeight();
    }
    // void setTexture(Image i);
    public void setHealth(int h)
    {
        player.setHealth(h);
    }
    public int getHealth()
    {
        return player.getHealth();
    }
    public java.util.List<Bullet>  getPlayerBullets()
    {
        return player.getPlayerBullets();
    }
    public void addNumOfBullets(int n)
    {
        player.addNumOfBullets(n);
    }
    public int getNumOfBullets()
    {
        return player.getNumOfBullets();
    }

    public Rectangle getBounds()
    {
        return player.getBounds();
    }
}
