// Jack Thompson
public class Cave {

    private Map map;
    private Hazzard hazzard;

    private int[] cords;
    
    public Cave(Map map, int x, int y, Hazzard h){
        build();
        this.hazzard = h;
    }

    public Cave(Map map, int x, int y){
        build();
    }

    private class build(Map map, int x, int y){
        this.map = map;
        this.cords = new int[]{xCord, yCord}
    }

}
