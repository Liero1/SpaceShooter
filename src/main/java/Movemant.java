import javax.swing.*;
import java.awt.*;

public class Movemant extends Bonus {

    //Dekorator który zmienia prędkośc

    //Zgodnie z uwagą pobieram w konstruktorze dodatkowy parametr
    //na podstawie którego zmniejszam/ zwiększam predkocc statku po OX
    //oraz zmieniam teksture

    public Movemant(IPlayer player, int d)
    {
        super(player);
        if(d == 1) {
            setDistance(2);
            setSpaceShipImage(IPlayer.pathToArcticShipTypeImg);
        }
        else if(d==3) {
            setDistance(4);
            setSpaceShipImage(IPlayer.pathToNeoShipTypeImg);
        }
        else
            setDistance(3);

    }
    public Rectangle getBounds()
    {
        return player.getBounds();
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


}
