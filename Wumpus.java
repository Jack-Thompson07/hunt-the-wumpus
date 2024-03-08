public class Wumpus {
    //////////////
    // Properties
    //////////////
    int position;
    boolean alive;
    Trivia trivia;
    Player player;
    ///////////////
    // Constructors
    //////////////
    public Wumpus(int roomNum,){
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

    public boolean isAlive(Player arrow){
        /*
          if(arrow.roomNum()== this.position){
            return false;
        }
        return true;
         */
       
    }
    
    public int returnWumpusPosition(){
        return this.position;
    }
}
