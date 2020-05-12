import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePlayer implements IPlayer {
    //Zmienne odpowiadające za położenie i ruch
    private int dx;
    private int dy;
    int x = 400;
    int y = 470;
    private int w;
    private int h;
    int distance;

    //Referencja do Board aby używać repaint w MoveLeft itp.
    public Board board;

    //Zmienne odpowiadające za pociski gracza
    private List<Bullet> playerBullets;
    int numOfBullets = 1500;
    int damage;


    int points;
    int health;
    Image shipImage;
    ImageIcon bulletImage;

    public GamePlayer(Board board)
    {
        initPlayer();
        this.board = board;

    }
    //Funkcje dostępu do pól klasy
    public void addNumOfBullets(int n)
    {
        this.numOfBullets += n;
    }
    public int getNumOfBullets()
    {
        return this.numOfBullets;
    }
    public int getWidth() {return this.w;}
    public int getHeight() {return this.h;}
    public Image getImage() {return this.shipImage;}
    public void setBulletTypeImg(ImageIcon img)
    {
        this.bulletImage = img;
    }

    //Wczytanie domyślnego obrazu Statku i domyślnego Pocisku
    private void loadImage()
    {
       setSpaceShipImage(pathToStandardShipTypeImg);


        bulletImage = new ImageIcon(pathToStandardBulletTypeImg);
    }

    public void setSpaceShipImage(String source)
    {
        ImageIcon img = new ImageIcon(source);
        shipImage = img.getImage();

        w = shipImage.getWidth(null);
        h = shipImage.getHeight(null);

    }

    private void initPlayer()
    {
        loadImage();
        playerBullets =  new CopyOnWriteArrayList<>();
        damage = 2;
        distance = 2;
    }
    public List<Bullet> getPlayerBullets()
    {
        return this.playerBullets;
    }
    public int getDistance(){return this.distance;}
    public int getDamage(){return this.damage;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public void setDamage(int d){this.damage = d;}
    public void setDistance(int d){this.distance = d;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y; }
    public void setHealth(int h) {this.health = h;}
    public int getHealth() {return this.health;}

    //Metody odpowiedzialne za poruszanie się statku
    //Zmienna dX to zmienna chwilowa. Dopóki ma ustawioną wartośc
    //x jest aktualizowane o wartość dx

    //Move odpowiada za aktualziacje koordynatów
    public void move()
    {
        //Przesunięcie
        x+= dx;
        //Kontrola zasięgu statku
        if (x <= 2) {
            x = 2;
        }

      if(x> (Board.WINDOW_X - getWidth() - 2))
          x = Board.WINDOW_X - getWidth() - 1;

    }
    //Obiekt prostokąta potrzebny przy szybkim sprawdzaniu kolizji
    //z bonusem, pociskiem wroga i wrogiem
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public void stopMoving()
    {
        dx = 0;
        board.repaint(this.getX()-1,this.getY() -1, this.getWidth()+2,this.getHeight()+2);
    }
    public void moveLeft()
    {
        dx = -distance;
        //move();
        board.repaint(this.getX()-1,this.getY() -1, this.getWidth()+2,this.getHeight()+2);
    }
    public void moveRight()
    {
        dx = distance;
        //move();
        board.repaint(this.getX()-1,this.getY() -1, this.getWidth()+2,this.getHeight()+2);
    }
    public void fire()
    {
        //Jezeli Player ma nadal pociski (zmienna numOfBullets dekrementowana po kazdym wystrzeleniu pocisku)
        //do Listy dodawany jest pocisk z aktualnymi koordynatami, aktalnymi obrazeniami i aktualnym obrazem
        if(numOfBullets!= 0) {
            playerBullets.add(new Bullet(x + getWidth() / 2 - 5, (y + getHeight() / 2) - 20, damage, bulletImage));
            numOfBullets-=1;
        }

    }



}
