public class MoveRight extends Action {
    //Element wzorca Command
    private IPlayer Player;
    public MoveRight(IPlayer player)
    {
        this.Player = player;
    }
    public void execute()
    {
        Player.moveRight();
    }
}
