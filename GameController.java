public class GameController {

    private GameLocations gl;
    private HighScore hs;
    private Gui gui;
    private Trivia trivia;

    private String mainState;

    private String[] currentQuestion;
    private int numCorrect;
    private int numRequired;
    private int numLeft;

    public GameController() {
        this.gl = new GameLocations();
        this.trivia = new Trivia();
        this.hs = new HighScore(this.gl.getPlayer());

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
            this.gl.getPlayer().addTurn();
            System.out.println("Player moved");
            this.gui.updateMapPanel();
            checkRoom();
        }

    }

    public void checkRoom() {
        if (gl.getHazardAt(this.gl.getPlayer().getPosition()) == null) {
            System.out.println("No Hazard");
        } else if (gl.getHazardAt(this.gl.getPlayer().getPosition()).equals("bat")) {
            System.out.println("Bat");
            this.gui.displayMessage("<html>You walk into the room and hear the sound of fluttering wings.<br>You look up and see hundreds of bats flying tward you.<br>They pick you up and carry you away while you struggle to fight them off.<br><br>YOU RAN INTO BATS!<br>THEY WILL CARRY YOU TO A NEW RANDOM ROOM!</html>", "BatsImage.png");
            batCarry();
        }

        else {
            System.out.println("Pit");
            this.gui.displayMessage("<html>You walk into the room an feel a weightless sensation.<br>You look down and see nothing below your feet.<br>You quickly grab on to the ledge struggling to hold on.<br><br>YOU RAN INTO A BOTTOMLESS PIT!<br>YOU MUST ANSWER TRIVIA QUESTION TO SURVIVE!</html>", "PitImage.png");
            this.numCorrect = 0;
            this.numLeft = 5;
            this.numRequired = 3;
            doAction("question");
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

        if(action.equals("question")){
System.out.println("ask question");
            if(this.numLeft > 0){
                this.currentQuestion = this.trivia.getNextQuestion();
                this.gui.displayQuestion(this.currentQuestion);
                numLeft --;
            }
            else{
                if(numCorrect >= numRequired){
                    System.out.println("y");
                    this.gui.displayMapPanel();
                }
                else{
                    System.out.println("n");
                    gameOver();
                }
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

    //return true if all answered the required amount correctly

    public void batCarry(){
        int[] cords = {(int)(Math.random() * 5),(int)(Math.random() * 6)};
        this.gl.getPlayer().move(cords);
        this.gui.updateMapPanel();
        checkRoom();
    }

    public void answerQuestion(String answer){
        if(this.trivia.isCorrectAnswer(this.currentQuestion[0],answer))
            this.numCorrect ++;
        doAction("question");
    }

    public void gameOver(){
         System.out.println("GAME ENDED");
        this.mainState = "end";
        this.gl.getPlayer().calculateScore(this.gl.wumpusAlive());
        
        this.hs.updateHighScoreValueIfNewHighScore();
        // Debugging statements
        System.out.println("Player Score: " + this.gl.getPlayer().getScore());
        System.out.println("Updating High Scores");
        
        this.hs.updateHighScoreValueIfNewHighScore();

        // Ensure to call only the update method to write scores
        this.hs.updateAllHighScores();

        // Optional: Print high scores to verify
        this.hs.printHighScore();
    }

}




















/*  
 import java.util.Random;

public class GameController {

    private GameLocations gl;
    private HighScore hs;
    private Gui gui;

    public GameController() {
        this.gl = new GameLocations();
        this.gui = new Gui(this);
        this.gui.displayMapPanel();
        placeChestsRandomly(5); // Placing 5 chests randomly on the map
    }

    // Method to place chests randomly on the map
    public void placeChestsRandomly(int numberOfChests) {
        Random random = new Random();
        for (int i = 0; i < numberOfChests; i++) {
            int x = random.nextInt(gl.getCave().getWidth());
            int y = random.nextInt(gl.getCave().getHeight());
            int[] position = { x, y };
            Chest.Content content = random.nextBoolean() ? Chest.Content.GOLD : Chest.Content.ARROWS;
            int quantity = random.nextInt(10) + 1; // Random quantity between 1 and 10
            Chest chest = new Chest(content, quantity, position);
            gl.getCave().placeChest(position, chest);
        }
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
            checkNearbyRoomsForWumpus(gl.getCave().getRoomAtPosition(gl.getPlayer().getPosition()));
            checkForChest(gl.getPlayer().getPosition());
        }
    }

    public void checkHazard() {
        if (gl.getHazardAt(this.gl.getPlayer().getPosition()) == null) {
            System.out.println("No Hazard");
        } else if (gl.getHazardAt(this.gl.getPlayer().getPosition()).equals("bat")) {
            System.out.println("Bat");
            this.gui.displayMessage("BATS", "image-removebg-preview (27).png");
        } else {
            System.out.println("Pit");
        }
    }

    public void checkNearbyRoomsForWumpus(Room room) {
        List<Room> adjacentRooms = room.getAdjacentRooms();
        for (Room adjacentRoom : adjacentRooms) {
            if (adjacentRoom.hasWumpus()) {
                System.out.println("You smell a Wumpus nearby!");
                return;
            }
        }
        System.out.println("There are no Wumpuses nearby.");
    }

    public void checkForChest(int[] position) {
        Chest chest = gl.getCave().getChestAtPosition(position);
        if (chest != null) {
            chest.openChest(gl.getPlayer());
            System.out.println("You found a chest containing " + chest.getQuantity() + " " + chest.getContent().name().toLowerCase() + "!");
            gl.getCave().removeChest(position); // Assuming there's a method to remove the chest
        }
    }

    public void doAction(String action) {
        if (action.equals("start game")) {

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
 */