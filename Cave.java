// Jack Thompson
public class Cave {

    private Map map;
    //private Hazzard hazzard;

    private int[] cords;
    
    /* 
    public Cave(Map map, int x, int y, Hazzard h){
        build(map,x,y);
        
    }
    */
    
    public Cave(Map map, int x, int y){
        build(map,x,y);
    }

    private void build(Map map, int x, int y){
        this.map = map;
        this.cords = new int[]{x, y};
    }

}
