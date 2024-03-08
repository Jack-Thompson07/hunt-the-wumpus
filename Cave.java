// Jack Thompson



import java.util.random.;

public class Cave {

    private Map map;
    private Hazzard hazzard;
    

    private int[] cords;
    
    /* 
    public Cave(Map map, int x, int y, Hazzard h){
        build(map,x,y);
        
    }
    */
    
    public Cave(Map map, int x, int y, Hazzard h){
        this.cords = new int[]{x,y};
        this.hazzard = h;
    }



}
