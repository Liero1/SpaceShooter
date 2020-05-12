public class StopMoving extends Action {
    //Element wzorca Command
    private IPlayer player;
    public StopMoving(IPlayer player) {
        this.player = player;
    }
    public void execute()
    {
        player.stopMoving();
    }
}
