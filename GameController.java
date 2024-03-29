import java.util.Random;

public class GameController {
    
    private Random r = new Random();
    private GameLocations map;
    
    

    public GameController(){
        this.map = new GameLocations();
    }

    //Called by GUI
    //Returns if the player is able to move to the given cords
    //If the player is able to move there, it will return true, and it will move the player there. 
    public boolean movePlayer(int[] cords){
        boolean validMove;

        validMove = this.map.checkValidMove(cords);

        if(validMove){
            this.map.setPlayerLocation(cords);
        }

        return validMove;
    }

    //Returns wether or not the player is in the same room as a chest
    public boolean checkForChest(){
        boolean isChest;

        isChest = this.map.checkChestWithPlayer();

        return isChest;
    }

    //returns wether or not the wampus is dead
    public boolean checkWampusAlive(){
        boolean alive;

        alive = this.map.checkWampusAlive();

        return alive;
    }

    
    
}
