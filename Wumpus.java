public class Wumpus {
    //////////////
    // Properties
    //////////////
    int position;
    boolean alive;
    Player player;
    int Xcor;
    int Ycor;

    ///////////////
    // Constructors
    //////////////
    public Wumpus(int x, int y){
        this.Xcor = x;
        this.Ycor = y;


    }

    //////////////
    // Methods
    /////////////

    public void kill(Player player){
        player.die();
    }

    
    public int getXPosition(){
        return this.Xcor;
    }
    public int getYPosition(){
        return this.Ycor;
    }
}
