// Laksh
public class Player {

   
    public int positon;
    public Player player;
    int Xcor;
    int Ycor;
    boolean alive;
    int moves;
    

    
    public Player(int x, int y){
        this.Xcor = x;
        this.Ycor = y;

    }
    public Player (int[] grid){

    }

   
    public void move(int x, int y){

    }

    public void addMove(){
        this.moves++;
    }

    public void die(){
        this.alive = false;
    }
    
    public int getPosition(){
        return this.positon;
    }

   
    public void updateXPosition(int newPos){
        this.Xcor = newPos;
    }
    public void updateYPosition(int newPos){
        this.Ycor = newPos;
    }
    
}
