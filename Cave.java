import java.util.ArrayList;

public class Cave {

    private Hazard hazard;


    private ArrayList<Integer> tunnels;
    
    private int index;
    private Player player;

    public Cave(int index){
        this.index = index;
        this.tunnels = new ArrayList<Integer>();
    }

    public Cave(int index, Hazard h){
        this.hazard = h;
    }

    public void playerComes(Player p){
        this.player = p;
    }

    public void playerLeaves(){
        this.player = null;
    }

    public ArrayList <Integer> getTunnels(){
        return this.tunnels;
    }

    public void addTunnel(int index){
        this.tunnels.add(index);
    }

    public int getIndex(){
        return this.index;
    }

}