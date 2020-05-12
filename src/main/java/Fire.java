public class Fire extends Action {

    //Element wzorca Command
    private IPlayer Player;
    public Fire(IPlayer player) {
        this.Player = player;
    }
    public void execute()
    {
        Player.fire();
    }
}
