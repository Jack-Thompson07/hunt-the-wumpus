
// Jack Thompson

import java.util.Scanner;
import java.io.File;

public class GameLocations {


    private Player player;
    
    private Cave cave;

    private String[][] hazards;

    // bats, caves

    public GameLocations(String name) {
        this.cave = new Cave();
        placeHazards();
        placePlayer(name);
        
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
            
            this.hazards[row][col] = "bat";
        }

        
        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }
            this.hazards[row][col] = "pit";
        }
    }

    public void placePlayer(String name){
        int col = -1;
        int row = -1;
        while((row == -1) || (this.hazards[row][col] != null)){
            row = (int)(Math.random() * 5);
            col = (int)(Math.random() * 6);
        }
        this.player = new Player(name,new int[]{row,col});
    }

    public boolean wumpusAlive(){
        return true;
    }
}
