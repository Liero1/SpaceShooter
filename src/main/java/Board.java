import edu.emory.mathcs.backport.java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements Runnable {

    private static Board boardInstance = null;
    public static Board getInstance()
    {
        if(boardInstance == null)
        {
            boardInstance = new Board();
            return boardInstance;
        }
        else
            return boardInstance;

    }


    static final int WINDOW_H = 600;
    static final int WINDOW_X  = 800;

    private Thread animator;
    private IPlayer player;
    private final int DELAY = 17;
    java.util.List<Bullet> playerBulletes;
    private long end;
    private NewBonus newBonus = null;
    long startTime;
    int points = 0;
    boolean inGame = true;
    long startEniemiesTime;
    long endEnemiesTime;

    java.util.List<Enemy> enemiesList;

    void checkColisionWithBonus()
    {
        //Aby sprawdzić kolizje tworzone są obiekty rectangle
        //reprezentujące Playera i Bonus

        //Wywolanie metody intersects sprawdza wszystkie
        //kombinacje zderzenia
       Rectangle shipRec = player.getBounds();
       Rectangle bonusRec = newBonus.getBounds();


        //Jezeli nastapila kolizja obiekt player zostaje opakowany
        //w Randomowy Bonus - Wzorzec Dekorator
       if(shipRec.intersects(bonusRec))
       {
           newBonus.setVisible(false);
           Random rnd = new Random();
           int x = rnd.nextInt(4) + 1;
           if(x==1)
               player = new Movemant(player,1);
           else if(x==2)
                player = new Movemant(player,3);
           else if(x==3)
                player = new ExtraDamage(player);
           else if(x==4)
               player = new DecreaseDamage(player);

       }

    }

    void generateNewEnemies()
    {
        if(enemiesList == null)
            enemiesList =  new CopyOnWriteArrayList();
        else{

            Random rnd = new Random();

            //n - liczba przeciwnikow
            //h - punkty życia przeciwników
            //d - dystansz jaki pokonuja przeciwnicy(szybkosc OY)
            //s - potrzebne do wybraia pierwszej wsp OX dla pierwszego z kolei przeciwnika
            int n,h,d,s;
            n = rnd.nextInt(5) + 1;
            h = rnd.nextInt(4) + 2;
            d = rnd.nextInt(3) + 2;
            s = rnd.nextInt(3) + 1;

            //Na podstawie wyolosowanej zmiennej wybieram koordynat X
            //dla pierwszego statku przeciwnika z lewej
            int x1 = 0;
            if(s==1)
                x1 = 30;
            else if(s==2)
                x1 = 200;
            else if(s==3)
                x1 = 400;

            //Tworze pierwszy Enemy aby pobrać zmienną W
            Enemy first = new Enemy(x1,0,h,d);
            int w = first.getW();

            //Dodaje do glownej listy
            enemiesList.add(first);

            for (int i = 0 ; i < n-1;i++)
            {
                x1+= w;
                first = new Enemy(x1,0,h,d);
                enemiesList.add(first);

            }


        }

    }

    void checkBulletColisionWithEnemies()
    {
        Rectangle r1;
        Rectangle r2;
        int eN = enemiesList.size();
        int bB = playerBulletes.size();
        //Sprawdzenie czy zaden pocisk nie dosięgnął enemy
        Iterator itEnemy = enemiesList.iterator();
        Iterator itBullet = playerBulletes.iterator();

        for(Enemy enemy : enemiesList) {
            for (Bullet bullet : playerBulletes) {
                System.out.println("Bullet dmg:" + bullet.getDmg());
                System.out.println("Enemy live points: " + enemy.getHealth());
                r1 = enemy.getBounds();
                r2 = bullet.getBounds();

                //Jeżeli nastąpiła kolizja
                if (r1.intersects(r2)) {

                    //Odjęcie punktów
                    enemy.subtractHealth(bullet.getDmg());
                    //Jeżeli liczba życia jest ujemna lub równa zero
                    //gracz uzyskuje punkt, znika przeciwnik i pocisk
                    if(enemy.getHealth() <= 0) {
                        enemy.setVisible(false);
                        bullet.setVisible(false);
                        points++;
                        continue;

                    }
                    else if(enemy.getHealth() > 0)
                    {
                        enemy.setVisible(true);
                        bullet.setVisible(false);
                        continue;

                    }

                    System.out.println("Liczba punktow: " +points);
                }


            }
        }


    }

    void checkCholisionEnemyWithPlayer()
    {
        Rectangle r1;
        Rectangle r2;
        for(Enemy enemy : enemiesList) {
                r1 = enemy.getBounds();
                r2 = player.getBounds();
                //Jeżeli nastąpiła kolizja
                if (r1.intersects(r2)) {
                    inGame = false;
                }


            }
    }
    public void gameOver(String message) {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, Board.WINDOW_X, Board.WINDOW_H);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, WINDOW_X / 2 - 30, WINDOW_X - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, WINDOW_X / 2 - 30, WINDOW_X - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (WINDOW_X - metr.stringWidth(message)) / 2,
                WINDOW_X / 2);

        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }*/
    }

    //NewBonus reprezntuje graficznie Bonus.
    //Nie ma zwiazku z Dekoratorem.

    //Metoda makeBonus po prostu zwraca obiekt newBonus
    NewBonus makeBonus()
    {

        //Losowanie randomowej zmiennej x po ktorej bedzie poruszal sie NewBonus
        Random rand = new Random();
        int x = rand.nextInt(WINDOW_X - 50) + 1;

        NewBonus bonus = new NewBonus(x,0);

        return bonus;
    }

    private Board() {

        player = new GamePlayer(this);

        //InputHandler() - wzorzec Command
        //Odpowiada za obsluge zdarzen plynacych z klawiatury
        KeyListener listener = new InputHandler();
        ((InputHandler) listener).setPlayer(player);
        addKeyListener(listener);

        setFocusable(true);
        setBackground(Color.black);

        //animator jest to wątek w ktorym odpalane sa animacje
        if(animator==null || !inGame)
        {
            animator = new Thread(this);
            animator.start();

            //dzieki tej zmiennej w parze z  zmienna end
            //mozna wprowadzic cykliczne zaachowania
            //np. co 10 sekund stworz nowy Bonus
            startTime = System.currentTimeMillis();
            startEniemiesTime = System.currentTimeMillis();
        }



    }


    //Rysowanie obiektów
    private void doDrawing(Graphics g) {


        if(inGame) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.drawImage(player.getImage(), player.getX(),
                    player.getY(), this);

            playerBulletes = player.getPlayerBullets();

            for (Bullet bullet : playerBulletes) {
                g2d.drawImage(bullet.getImage(), bullet.getX(),
                        bullet.getY(), this);
            }

            if (enemiesList != null) {
                for (Enemy enemy : enemiesList) {
                    g2d.drawImage(enemy.getShipImage(), enemy.getX(),
                            enemy.getY(), this);
                }
            }

            if (newBonus != null && newBonus.isVisible == true)
                g2d.drawImage(newBonus.getImage(), newBonus.getX(), newBonus.getY(), this);
        }
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void updateBullets() {
        java.util.List<Bullet> missiles = player.getPlayerBullets();

        for (int i = 0; i < missiles.size(); i++) {
            Bullet bullet = playerBulletes.get(i);
            if (bullet.isVisible()) {
                bullet.move();
            } else {

                missiles.remove(i);
            }
        }
    }
    private void updateEnemies() {
        for (int i = 0; i < enemiesList.size(); i++) {
            Enemy enemy = enemiesList.get(i);
            if (enemy.getVisible()) {
                //System.out.println((char)27 + "[31m" + "Player Bullet id" + i + " Y: " + bullet.getY());
                enemy.move();
            } else {
                enemiesList.remove(i);
            }
        }
    }

   // @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (inGame) {

            repaint();
            updateBullets();

            if(enemiesList!=null) {
                updateEnemies();
                checkBulletColisionWithEnemies();
                checkCholisionEnemyWithPlayer();
            }
            player.move();
            if(newBonus != null) {
                newBonus.move();
                checkColisionWithBonus();

            }

            timeDiff = System.currentTimeMillis() - beforeTime;

            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();


            end = System.currentTimeMillis() - startTime;
            endEnemiesTime = System.currentTimeMillis() - startEniemiesTime;
            if(end>9000)
            {
                newBonus = makeBonus();
                startTime = System.currentTimeMillis();

            }else if(endEnemiesTime>3000)
            {
                generateNewEnemies();
                startEniemiesTime = System.currentTimeMillis();
            }
        }
        gameOver("Przegrałeś :(");


    }



}
