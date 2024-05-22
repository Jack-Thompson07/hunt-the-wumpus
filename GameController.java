public class GameController {

    private GameLocations map;
    private Player player;
   


    public GameController(){
        this.player = new Player("LAKSH", new int[]{0,0});
        this.map = new GameLocations(this.player);
        
    }

    //Called by GUI
    //Returns if the player is able to move to the given cords
    //If the player is able to move there, it will return true, and it will move the player there. 
    public boolean movePlayer(int[] cords){

        boolean validMove = map.getCave(this.player.getPosition()).getTunnels().contains(this.map.getCave(cords).getIndex());

        if(validMove){
            this.player.move(cords);
            this.player.addMove();
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

    public Cave[][] getGrid(){
        return this.map.getGrid();
    }

    public boolean checkHazardInSameRoom(){

       if(map.getCave(this.player.getPosition()) != null){
            return true;
       }

    
    return false;
    }
}