import javax.swing.*;
import java.awt.*;

public class Application extends JFrame{


    public Application()
    {
        initUI();

    }
    private void initUI()
    {
        add(Board.getInstance());
        setSize(Board.WINDOW_X,Board.WINDOW_H);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Space Shooter");

    }


    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });

    }

}
