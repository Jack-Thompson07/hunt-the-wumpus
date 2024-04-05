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

    private final String[] hazzardTypes= {"Bat", "Pit"};
    private final int maxHazards = 2;

    {
        {{}{}{}}
        {}
        {}
    }

    private int[] totalHazards;

    private int[][][] locationsOfHazards = new int[this.hazzardTypes.length][maxHazards][2];
    // bats, caves

    public GameLocations() {
        this.grid = new Cave[SIZE][SIZE];
        this.p = new Painter(this);

        this.player = new Player(new int[] { r.nextInt(width), r.nextInt(height) });
    }

    public void build() {

        this.grid = new Cave[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[this.height][this.width] = new Cave(this, this.height, this.width);
            }
        }
    }

    private void setHazzardLocations(){
        for(int i = 0; i < this.locationsOfHazards.length; i ++){
            int count1 = 0;
            while (count1 < maxHazards){
                int x = r.nextInt(this.width);
                int y = r.nextInt(this.height);
                
                int count2 = 1;
                for(int j = 0; j < this.locationsOfHazards[i].length; j ++){
                    if(this.locationsOfHazards != null){
                        if(this.locationsOfHazards[i][j] == new int[]{x,y}){
                            count2 ++;
                        }
                    }
                }

                
            }
        }
    }

    public void draw() {
        p.draw();
    }

    public void setPlayerLocation(int[] cords) {

    }

    public boolean checkValidMove(int[] cords) {
        return true;
    }

    public boolean checkWampusAlive() {
        return true;
    }

    public boolean checkChestWithPlayer() {
        return true;
    }

    public void addHazard(String hazard) {
        if (hazard.equals("Bat"))
            this.totalHazards[0]++;
        else if (hazard.equals("Pit"))
            this.totalHazards[1]++;
    }

    public Player getPlayer() {
        return this.player;
    }

}