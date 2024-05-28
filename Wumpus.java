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
        this.alive = true;


    }
    //////////////
    // Methods
    /////////////

    public boolean die(){
       return this.alive = false;
    }
    public boolean isAlive(){
        return this.alive;
    }

    public void initiateTrivia(){
        System.out.println("WUMPUS ALERT");
    }

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
