// Jack Thompson

import java.util.Random;

public class Map {

    private int SIZE;
    
    private Cave[][] grid;
    private Painter p;
    private Random r = new Random();

    private final int height = 10;
    private final int width = 10;

    private final int maxHazzards = 2;

    private int[][][] locationsOfHazzards = new int[2][maxHazzards][2];
    // bats, caves

    
    
    
    public Map(){
        this.grid = new Cave[SIZE][SIZE];
        this.p = new Painter(this);

        
    }

    public void build(){
        for(int i = 0; i < this.locationsOfHazzards.length; i ++){
            for(int j = 0; j < this.locationsOfHazzards[i].length; j ++){
                int x = r.nextInt(this.width);
                int y = r.nextInt(this.height);

                this.locationsOfHazzards[i][j][0] = x;
                this.locationsOfHazzards[i][j][1] = y;
            }
        }

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

    public void addHazzard(String hazzard){
        if(hazzard.equals("Bat"))
            this.totalHazzards[0] ++;
        else if(hazzard.equals("Pit"))
            this.totalHazzards[1] ++;
    }

    public int[] getTotalHazzerd(){
        return this.totalHazzards;
    }


}
