// Jack Thompson
public class Map {

    private int SIZE;
    
    private Cave[][] grid;
    private Painter p;

    private final int height = 10;
    private final int width = 10;
    
    
    public Map(){
        this.grid = new Cave[SIZE][SIZE];
        this.p = new Painter(this);

        
    }

    public void build(){

        this.grid = new Cave[this.height][this.width];

        for(int i = 0; i < this.height; i ++){
            for(int j = 0; j < this.width; j ++){
                this.grid[this.height][this.width] = new Cave(this, this.height, this.width);
            }
        }
    }

    public void draw(){
        p.draw();
    }

    public void setPlayerLocation(int[] cords){

    }

    public boolean checkValidMove(int[] cords){
        return true;
    }

    public boolean checkWampusAlive(){
        return true;
    }

    public boolean checkChestWithPlayer(){
        return true;
    }
}
