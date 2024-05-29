public class GameController {

    private GameLocations gl;
    private HighScore hs;
    private Gui gui;

    private String mainState;

    public GameController() {
        this.gl = new GameLocations();
        this.gui = new Gui(this);

        this.mainState = "map";
        this.gui.displayMapPanel();
    }


    // Called by GUI
    // Returns if thePlayer is able to move to the given cords
    // If the Player is able to move there, it will return true, and it will move
    // the Player there.

    public void movePlayer(int[] cords) {

        boolean validMove = false;
        int[] tunnels = this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition());

        for (int i : tunnels) {
            if ((this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), i)[0] == cords[0])
                    && (this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), i)[1] == cords[1]))
                validMove = true;
        }

        if (validMove) {
            this.gl.getPlayer().move(cords);
            this.gl.getPlayer().addMove();
            System.out.println("Player moved");
            this.gui.updateMapPanel();
            checkHazard();
        }

    }

    public void checkHazard() {
        if (gl.getHazardAt(this.gl.getPlayer().getPosition()) == null) {
            System.out.println("No Hazard");
        } else if (gl.getHazardAt(this.gl.getPlayer().getPosition()).equals("bat")) {
            System.out.println("Bat");
            this.gui.displayMessage("<html>You walk into the room and hear the sound of fluttering wings.<br>You look up and see hundreds of bats flying tward you.<br>They pick you up and carry you away while you struggle to fight them off.<br><br>YOU RAN INTO BATS!<br>YOU MUST ANSWER TRIVIA QUESTION TO SURVIVE!</html>", "BatsImage.png");
    
        }

        else {
            System.out.println("Pit");
            this.gui.displayMessage("<html>You walk into the room an feel a weightless sensation.<br>You look down and see nothing below your feet.<br>You quickly grab on to the ledge struggling to hold on.<br>YOU RAN INTO A BOTTOMLESS PIT!<br>YOU MUST ANSWER TRIVIA QUESTION TO SURVIVE!</html>", "PitImage.png");
        }
    }

    public void doAction(String action) {
        if (action.equals("start game")) {
            
        }
        if(action.equals("continue")){
            System.out.println("Continued");
            if(mainState.equals("map")){
                this.gui.displayMapPanel();
            }
        }
    }

    public int[] getTunnels(int[] cords) {
        return this.gl.getCave().getTunnels(cords);
    }

    public int[] getPlayerPosition() {
        return this.gl.getPlayer().getPosition();
    }

    public GameLocations getGameLocations() {
        return this.gl;
    }
}
