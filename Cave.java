// Jack Thompson



import java.util.Random;

public class Cave {

    private GameLocations map;
    private Hazard hazard;
    

    private int[] cords;
    private Player player;
    
    public Cave(GameLocations map, int x, int y){
        this.cords = new int[]{x,y};
    }
    
    public Cave(GameLocations map, int x, int y, Hazard h){
        this.cords = new int[]{x,y};
        this.hazard = h;
    }
    public Cave(String name){
        
    }

    public void playerComes(Player p){
        this.player = p;
    }

    public void playerLeaves(){
        this.player = null;
    }




}