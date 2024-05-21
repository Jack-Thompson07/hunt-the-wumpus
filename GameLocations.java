// Jack Thompson

import java.util.Scanner;
import java.io.File;

public class GameLocations {

    private final int HEIGHT = 5;
    private final int WIDTH = 6;

    private Player player;
    private Hazard hazard;

    private Cave[][] grid;
    
    Scanner reader;
    String[] data;

    private int[] totalHazards;

    // bats, caves

    public GameLocations(Player p) {
        this.grid = new Cave[HEIGHT][WIDTH];

        this.player = p;
        this.hazard = hazard;
        readFile();
        build();
    }

    public void build() {

        this.grid = new Cave[HEIGHT][WIDTH];

       for(int i = 0; i < HEIGHT; i ++){
           for(int j = 0; j < WIDTH; j ++){
               Cave current = this.grid[i][j] = new Cave((i * WIDTH + j + 1));
               for(String s : this.data[(i * WIDTH + j)].split(",")){
                    current.addTunnel(Integer.parseInt(s));
               }
           }
       }    
    }


    public void setPlayerLocation(int[] cords) {

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

    public Cave[][] getGrid(){
        return this.grid;
    }
    
    public Hazard getHazard(){
        return this.hazard;
    }

    public void readFile(){
        
        File f = new File("StoredGrids.csv"); 
        try {
          this.reader = new Scanner(f);
        }
        catch(Exception e){
            System.out.println("<No File Found>");
        }
        /*for(int i = 0; i < (int)(Math.random() * 5))
            reader.nextLine();*/
        
        this.data = reader.nextLine().split(";");
    }

    public Cave getCave(int[] cords){
        return this.grid[cords[0]][cords[1]];
    }


}
