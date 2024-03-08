public class GameController {
    
    private Map map;
    private Player player;

    public GameController(){
        this.map = new Map();
        this.player = new Player();

    }

    public boolean movePlayer(int[] cords){
        boolean validMove;

        validMove = this.map.checkValidMove(cords);

        if(validMove){
            this.map.setPlayerLocation(cords);
        }

        return validMove;
    }

    public boolean checkForChest(){
        boolean isChest;

        isChest = this.map.checkChestWithPlayer();

        return isChest;
    }

    public boolean checkWampusAlive(){
        boolean alive;

        alive = this.map.checkWampusAlive();

        return alive;
    }

    public void addMove(){
        this.player.addMove();
    }
}
