public class GameController {

    private GameLocations gl;
    private Gui gui;


    public GameController(){
        this.gui = new Gui(this);
        this.gui.mainMenu();
        
    }

    //Called by GUI
    //Returns if the player is able to move to the given cords
    //If the player is able to move there, it will return true, and it will move the player there. 
    public boolean movePlayer(int[] cords) {

        boolean validMove = false;
        int[] tunnels = this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition());

        for (int i : tunnels) {
            if (this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), i) == cords)
                validMove = true;
        }

        if (validMove) {
            this.gl.getPlayer().move(cords);
            this.gl.getPlayer().addMove();
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

        this.gui.map();
    }
}
