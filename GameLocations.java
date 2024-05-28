
// Jack Thompson

import java.util.Scanner;
import java.io.File;

public class GameLocations {


    private Player player;
    
    private Cave cave;

    private String[][] hazards;

    // bats, caves

    public GameLocations(Player player) {
        this.cave = new Cave();
        this.player = new Player("Joe",new int[]{0,0});

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
}
