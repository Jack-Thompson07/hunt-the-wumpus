// Laksh
public class Player {

    private boolean alive = true;
    private int moves;
    private int[] positon;

    private HighScore hs;

    public Player(int[] cords)){
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
    
    private int[] getPosition(){
        return this.positon;
    }
}
