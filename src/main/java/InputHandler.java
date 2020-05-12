import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

/*InputHandler jest odpowiednikiem Invoker z wzorca Command. Jako parametr w
konstruktorze przyjmujemy refernecje do obiektu Player. Implementuje on klasę
KeyAdapter. Metoda keyPressed() służy do sprawdzenia wciśniętego klawisza na
podstawie którego tworzony jest obiekt dziedziczący(ConcreteCommand) z
Action(Command) oraz wywoływana jest metoda placeAction(action: Action) dodająca
do kolejki inputQueue ostatnio stworzony obiekt a następnie wykonywująca metodę
execute() pierwszego obiektu w kolejce. Konkretna metoda execute() wykonuje
konkretną metodę obiektu Player(moveLeft(), moveRight(), fire()) w
których znajduje się implementacja odpowiednich czynności. Zastosowanie
wzorca Command powoduje stworzenie warstwy abstrakcji pomiędzy klawiaturą a
implementacją czynności oraz gwarantuje wykonanie wszystkich poleceń użytkownika. */
public class InputHandler extends KeyAdapter {
    private IPlayer player;

    //Kolejka czynnosci do wykonania
    private Queue<Action> actionQueue;
    //Tutaj dodac pobieranie PLayera z argumentu
    public InputHandler()
    {
        actionQueue = new LinkedList<>();
    }
    public void setPlayer(IPlayer p)
    {
        this.player = p;

    }
    private void placeAction(Action action)
    {
        //Nowa akcja zostaje dodana do kolejki
        //sciagana jest akcja z gory i wykonywana
        actionQueue.add(action);
        Action a = actionQueue.poll();
        a.execute();
    }
    //Reakcja na wscisniecie klawisza
    @Override
    public void  keyPressed(KeyEvent evt)
    {
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            placeAction(new MoveLeft(player));

        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            placeAction(new MoveRight(player));
        }
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            placeAction(new Fire(player));
        }

    }
    //Reakcja na puszczenie klawisza
    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            placeAction(new StopMoving(player));
        }

        if (key == KeyEvent.VK_RIGHT) {
            placeAction(new StopMoving(player));

        }

    }
}
