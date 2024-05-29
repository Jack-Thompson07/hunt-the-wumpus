
// Jack Thompson

import java.util.Scanner;
import java.io.File;

public class GameLocations {


    private Player player;
    
    private Cave cave;

    private String[][] hazards;

    private String[][] wumpus;

    // bats, caves

    public GameLocations() {
        this.cave = new Cave();
        placeHazards();
        placePlayer();
        
    }

    public Cave getCave(){
        return this.cave;
    }

    public String getHazardAt(int[] cords){
        return this.hazards[cords[0]][cords[1]];
    }

    public Player getPlayer(){
        return this.player;
    }

    public void placeHazards(){
        this.hazards = new String[5][6];
        
        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }
            System.out.println(row + " - " + col);
            this.hazards[row][col] = "bat";
        }

        
        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }
            System.out.println(row + " - " + col);
            this.hazards[row][col] = "pit";
        }
    }

    public void placePlayer(){
        int col = -1;
        int row = -1;
        while((row == -1) || (this.hazards[row][col] != null)){
            row = (int)(Math.random() * 5);
            col = (int)(Math.random() * 6);
        }
        this.player = new Player(new int[]{row,col});
    }

    public void WumpusPlace(){
        this.wumpus = new String[5][6];
        
        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.wumpus[row][col] != null) || (this.player.getPosition()[0] == row && this.player.getPosition()[1] == col)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }
            System.out.println(row + " - " + col);
            this.wumpus[row][col] = "Wumpus";
        }
    }
}
