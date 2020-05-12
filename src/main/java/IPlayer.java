import javax.swing.*;
import java.awt.*;

public interface IPlayer {

    //Lokalizacja obrazów
    String pathToStandardShipTypeImg = "src/main/resources/SpaceShip.png";
    String pathToArcticShipTypeImg = "src/main/resources/SpaceShipArctic.png";
    String pathToNeoShipTypeImg = "src/main/resources/SpaceShipNeo.png";
    String pathToStandardBulletTypeImg = "src/main/resources/BulletOrg.png";
    String pathToExtraDmgBulletTypeImg = "src/main/resources/BulletRed.png";
    String pathToLessDmgBulletTypeImg = "src/main/resources/BulletBlue.png";

    Rectangle getBounds();
    int getDistance();
    int getDamage();
    int getX();
    int getY();
    void setDamage(int d);
    void setDistance(int d);
    void setX(int x);
    void setY(int y);
    void moveLeft();
    void moveRight();
    void stopMoving();
    void fire();
    Image getImage();
    void move();
    int getWidth();
    int getHeight();
    void setHealth(int h);
    int getHealth();
    java.util.List<Bullet>  getPlayerBullets();
    void addNumOfBullets(int n);
    int getNumOfBullets();
    void setBulletTypeImg(ImageIcon img);
    void setSpaceShipImage(String source);

}
