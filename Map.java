// Jack Thompson
public class Map {

    private int SIZE;
    
    private Cave[][] grid;
    private painter p;
    
    
    public Map(){
        this.grid = new grid[SIZE][SIZE];
        this.p = new Painter(this);
    }

    public void draw(){
        p.draw();
    }
}
