// Laksh
public class Player {

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    //      Need to create a CSV File using UUID and other 
    //        stats for player
    //      the UUID connects the high score to the player
    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////


    private boolean alive = true;
    private int moves;
    public int[] positon;

    private HighScore hs;

    public Player(int[] cords){
        this.positon = cords;
    }

    public void move(int x, int y){

    }

    public void addMove(){
        this.moves ++;
    }

    public void die(){
        this.alive = false;
    }
    
    public int[] getPosition(){
        return this.positon;
    }

    public void updatePosition(int[] newPos){
        this.position = newPos;

    }
    
}
