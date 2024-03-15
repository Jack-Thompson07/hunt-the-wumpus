import java.util.Random;

public class GameControllerTest {
    
    private Random r = new Random();
    private Map map;
    private Player player;

    public GameControllerTest(){
        this.map = new Map();
        this.player = new Player(new int[]{0,0});
    }

    //Called by GUI
    //Returns if the player is able to move to the given cords
    //If the player is able to move there, it will return true, and it will move the player there. 
    public boolean movePlayer(int[] cords){
        boolean validMove;

      System.out.println("Checking if the move is valid...");

        validMove = this.map.checkValidMove(cords);

        if(validMove){
            System.out.println("This is a valid move, the player is now moving there");
            this.map.setPlayerLocation(cords);
        }

        return validMove;
    }

    //Returns wether or not the player is in the same room as a chest
    public boolean checkForChest(){
        boolean isChest;

        System.out.println("Checking if there is a chest...");
        
        isChest = this.map.checkChestWithPlayer();

        System.out.println(isChest);
      
        return isChest;
    }

    //returns wether or not the wampus is dead
    public boolean checkWampusAlive(){
        boolean alive;

        System.out.println("Checking if the wampus is alive...");
      
        alive = this.map.checkWampusAlive();

        System.out.println(alive);
      
        return alive;
    }

    public void addMove(){

        System.out.println("A move has passed");
      
        this.player.addMove();
    }
    
}
