// Laksh
public class Player {

    private boolean alive = true;
    private int moves;
    public int positon;

    private HighScore hs;

    public Player(int cords){
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
    
    public int getPosition(){
        return this.positon;
    }

    public void updatePosition(int newPos){
        this.position = newPos;

    }
    
}
