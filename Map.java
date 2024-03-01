// Jack Thompson
public class Map {

    private int SIZE;
    
    private Cave[][] grid;
    private Painter p;
    
    
    public Map(){
        this.grid = new Cave[SIZE][SIZE];
        this.p = new Painter(this);
    }

    public void draw(){
        p.draw();
    }
}
