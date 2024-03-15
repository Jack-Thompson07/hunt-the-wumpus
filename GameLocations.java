// Jack Thompson

import java.util.Random;

public class GameLocations {

    private int SIZE;

    private Player player;
    
    private Cave[][] grid;
    private Painter p;
    private Random r = new Random();

    private final int height = 10;
    private final int width = 10;

    private final int maxHazards = 2;

    private int[] totalHazards;

    private int[][][] locationsOfHazards = new int[2][maxHazards][2];
    // bats, caves

    
    
    
    public GameLocations(){
        this.grid = new Cave[SIZE][SIZE];
        this.p = new Painter(this);

        this.player = new Player(new int[]{r.nextInt(width), r.nextInt(height)});
    }

    public void build(){
        for(int i = 0; i < this.locationsOfHazards.length; i ++){
            for(int j = 0; j < this.locationsOfHazards[i].length; j ++){
                int x = r.nextInt(this.width);
                int y = r.nextInt(this.height);

                this.locationsOfHazards[i][j][0] = x;
                this.locationsOfHazards[i][j][1] = y;
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

    public void addHazard(String hazard){
        if(hazard.equals("Bat"))
            this.totalHazards[0] ++;
        else if(hazard.equals("Pit"))
            this.totalHazards[1] ++;
    }

    public int[] getTotalHazzerd(){
        return this.totalHazards;
    }


}
