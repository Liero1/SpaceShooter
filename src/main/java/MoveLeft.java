public class MoveLeft extends Action {

    //Element wzorca Command

    private IPlayer Player;
    public MoveLeft(IPlayer player)
    {
        this.Player = player;
    }
    public void execute()
    {
        Player.moveLeft();
    }
}
