public class GameController {

    private GameLocations gl;
    private HighScore hs;
    private Gui gui;

    public GameController() {
        this.gl = new GameLocations();
        this.gui = new Gui(this);
    }

    // Called by GUI
    // Returns if thePlayer is able to move to the given cords
    // If the Player is able to move there, it will return true, and it will move
    // the Player there.

    public boolean movePlayer(int[] cords) {

        boolean validMove = false;
        int[] tunnels = this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition());

        for (int i : tunnels) {
            if ((this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), i)[0] == cords[0]) && (this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), i)[1] == cords[1]))
                validMove = true;
        }

        if (validMove) {
            this.gl.getPlayer().move(cords);
            this.gl.getPlayer().addMove();
            System.out.println("Player moved");
            this.gui.updateMap();
        }

        return validMove;
    }

     
     public String checkHazard(){
     
        if(gl.getHazardAt(this.gl.getPlayer().getPosition()) == null){
            return null;
        }

        String type = gl.getHazardAt(this.gl.getPlayer().getPosition());

         

         return type;
     }

    public void doAction(String action){
        if(action.equals("start game")){
            startGame();
        }
    }

    public void startGame(){
        System.out.println("Game Started");
        this.gl = new GameLocations();
        this.gui.wipe();
    }

    public int[] getTunnels(int[] cords){
        return this.gl.getCave().getTunnels(cords);
    }

    public int[] getPlayerPosition(){
        return this.gl.getPlayer().getPosition();
    }

    public GameLocations getGameLocations(){
        return this.gl;
    }
}