public class Wumpus {
    //////////////
    // Properties
    //////////////
    int position;
    boolean alive;
    Player player;

    ///////////////
    // Constructors
    //////////////
    public Wumpus(int roomNum){
        this.position = roomNum;
    }

    //////////////
    // Methods
    /////////////

    public void kill(Player player){
        if(player.getPosition() == this.position){
            player.die();
        }
    }

    
    
    public int returnPosition(){
        return this.position;
    }
}
