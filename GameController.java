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

        this.trivia = new Trivia();
        this.hs = new HighScore();

        this.gui = new Gui(this);
        this.mainState = "start";
        this.gui.displayStartPanel(this.hs.getAllHighScores());
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
            this.gl.getPlayer().addTurn();
            updateGame();
        }

    }

    public void updateGame(){
        System.out.println("game updated");
        if(!this.gl.getWumpus().getAlive()){
            this.mainState = "gameOver";
            this.gui.displayMessage("YOU KILLED THE WUMPUS", "VictoryImage.png");
        }
        else{
            this.gui.updateMapPanel();
            this.gl.getWumpus().newTurn();
            checkNearby();
        }

    }

    public void checkRoom() {
        if(gl.getWumpus().getPos()[0] == gl.getPlayer().getPosition()[0] && gl.getWumpus().getPos()[1] == gl.getPlayer().getPosition()[1]){
            this.mainState = "wumpus";
            System.out.println("wumpus");
            this.gui.displayMessage("YOU RAN INTO THE WUMPUS","WumpusImage.png");
        }
        else if(gl.getHazardAt(this.gl.getPlayer().getPosition()) == null);
        else if (gl.getHazardAt(this.gl.getPlayer().getPosition()).equals("bat")) {
            System.out.println("Bat");
            this.gui.displayMessage("<html>You walk into the room and hear the sound of fluttering wings.<br>You look up and see hundreds of bats flying tward you.<br>They pick you up and carry you away while you struggle to fight them off.<br><br>YOU RAN INTO BATS!<br>THEY WILL CARRY YOU TO A NEW RANDOM ROOM!</html>", "BatsImage.png");
            batCarry();
        }

        else {
            System.out.println("Pit");
            this.mainState = "pit";
            this.gui.displayMessage("<html>You walk into the room an feel a weightless sensation.<br>You look down and see nothing below your feet.<br>You quickly grab on to the ledge struggling to hold on.<br><br>YOU RAN INTO A BOTTOMLESS PIT!<br>YOU MUST ANSWER 3 OUT OF 5 TRIVIA QUESTIONS TO SURVIVE!</html>", "PitImage.png");

        }
    }

    public void checkNearby(){
        boolean bat = false;
        boolean pit = false;
        boolean wumpus = false;
        for(int i = 0; i < 6; i ++){
                if(i == this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition())[0] || i == this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition())[1] || i == this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition())[2]){
                    int[] cords = gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(),i);
                    System.out.println(this.gl.getHazardAt(cords));
                    if(this.gl.getHazardAt(cords) == null);
                    else if(this.gl.getHazardAt(cords).equals("bat")){
                    bat = true;
                }
                else if(this.gl.getHazardAt(cords).equals("pit")){
                    pit = true;
                }
                if(cords[0] == this.gl.getWumpus().getPos()[0] && cords[1] == this.gl.getWumpus().getPos()[1]){
                    wumpus = true;
                }
            }

        }

        if(bat || pit || wumpus){
            String text = "<html>";
            if(wumpus)
                text += "I SMELL A WUMPUS!<br>";
            if(pit)
                text += "I FEEL A DRAFT!<br>";
            if(bat)
                text += "I HEAR BATS!<br>";
            text += "</html>";
            this.mainState = "warning";
            this.gui.displayMessage(text,"HazardImage.png");
        }

        else{
            checkRoom();
        }
    }

    public void doAction(String action) {
        if (action.equals("start")) {
            this.gl = new GameLocations(this.gui.getStartText());
            this.hs.addMainPlayer(this.gl.getPlayer());
            this.mainState = "map";
            this.gui.displayMapPanel(this.gl.getPlayer());
            updateGame();
        }
        if(action.contains("arrow shot")){
            this.mainState = "arrow shot";
            System.out.println("Shot");
            this.gl.getPlayer().shootArrow();
            int direction = Integer.parseInt(action.substring(0,1));
            int[] cords = this.gl.getCave().getPosOfTunnel(this.gl.getPlayer().getPosition(), direction);
            if(cords[0] == this.gl.getWumpus().getPos()[0] && cords[1] == this.gl.getWumpus().getPos()[1]){
                this.gl.getWumpus().die();
                this.gui.displayMessage("YOU SHOT AND KILLED THE WUMPUS","VictoryImage.png");
            }
            else{
                this.gl.getWumpus().arrowMissed();
                this.gui.displayMessage("YOU SHOT AND MISSED THE WUMPUS","VictoryImage.png");
            }


        }
        if(action.equals("shoot arrow")){
            if(this.gl.getPlayer().getArrows() >= 1){
                this.mainState = "shoot arrow";
                this.gui.displayShootArrowPanel(this.gl.getCave().getTunnels(this.gl.getPlayer().getPosition()));
            }
        }
        if(action.equals("buy arrow")){
            if(this.gl.getPlayer().getCoins() >= 3){
                this.mainState = "buy arrow";
                this.gl.getPlayer().takeCoins(3);
                this.gui.displayMessage("YOU MUST ANSWER 2 OUT OF 3 QUESTIONS CORRECT TO GET AN ARROW", "ArrowImage.png");
            }
        }
        if(action.equals("buy secret")){
            if(this.gl.getPlayer().getCoins() >= 3){
                this.mainState = "buy secret";
                this.gl.getPlayer().takeCoins(3);
                this.gui.displayMessage("YOU MUST ANSWER 2 OUT OF 3 QUESTIONS CORRECT TO GET A SECRET", "SecretImage");
            }
        }
        if(action.equals("continue")){
            System.out.println("Continued");
            if(mainState.equals("map")){
                this.gui.displayMapPanel(this.gl.getPlayer());
            }
            if(this.mainState.equals("arrow shot")){
                this.mainState = "map";
                this.gl.getPlayer().addTurn();
                updateGame();
            }
            if(mainState.equals("gameOver")){
                gameOver(!this.gl.getWumpus().getAlive());
            }
            if(mainState.equals("pit")){
                this.numCorrect = 0;
                this.numLeft = 3;
                this.numRequired = 2;
                doAction("question");
            }
            if(mainState.equals("wumpus")){
                this.numCorrect = 0;
                this.numLeft = 5;
                this.numRequired = 3;
                doAction("question");
            }
            if(mainState.equals("warning")){
                this.mainState = "map";
                checkRoom();
            }
            if(mainState.equals("buy arrow")){
                this.numCorrect = 0;
                this.numLeft = 3;
                this.numRequired = 2;
                doAction("question");
            }
            if(mainState.equals("buy secret")){
                this.numCorrect = 0;
                this.numLeft = 3;
                this.numRequired = 2;
                doAction("question");
            }
        }

        if(action.equals("back")){
            System.out.println(this.mainState);
            if(this.mainState.equals("shoot arrow")){
                this.mainState = "map";
                this.gui.displayMapPanel(this.gl.getPlayer());
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
                    if(this.mainState.equals("pit")){
                        this.gui.displayMessage("<html>You take a deep breath and give one last effort to pull yourself up.<br>You are successful and are now out of the pit.<br>CONGRATULATIONS!<br>YOU SURVIVED THE HAZARD!</html>", "VictoryImage.png");
                    }
                    if(this.mainState.equals("wumpus")){
                        this.gl.getWumpus().defeated();
                        this.gui.displayMessage("YOU SURVIVED THE WUMPUS","VictoryImage.png");
                    }
                    if(this.mainState.equals("buy arrow")){
                        this.gl.getPlayer().addArrow();
                        this.mainState = "map";
                        this.gui.displayMessage("YOU RECIEVED AN ARROW!","ArrowImage.png");
                        this.gl.getPlayer().addTurn();
                    }
                    if(this.mainState.equals("buy secret")){
                        this.mainState = "map";
                        giveSecret();
                        this.gl.getPlayer().addTurn();

                    }
                    this.mainState = "map";
                    System.out.println("y");
                }
                else{
                    if(this.mainState.equals("pit")){
                        this.gui.displayMessage("<html>You try to pull your self up but you feel your fingers begin to slip.<br>Your hand gives way and you fall to your death.<br>YOU DIED!<br>THE GAME IS OVER!</html>", "LooseImage.png");
                        this.mainState = "gameOver";
                    }
                    if(this.mainState.equals("wumpus")){
                        this.gui.displayMessage("YOU DIED TO THE WUMPUS", "LooseImage.png");
                        this.mainState = "gameOver";

                    }
                    if(this.mainState.equals("buy arrow")){
                        this.gui.displayMessage("YOU DID NOT RECIEVE AN ARROW", "SadImage.png");
                        this.mainState = "map";
                        this.gl.getPlayer().addTurn();
                    }
                    if(this.mainState.equals("buy secret")){
                        this.gui.displayMessage("YOU DID NOT RECIEVE A SECRET", "SadImage.png");
                        this.mainState = "map";
                        this.gl.getPlayer().addTurn();
                    }
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

    public void giveSecret(){

        System.out.println("giving secret");
        String secret = "";
        double ran = Math.random();
        if(ran < 0.2){
            secret = "THE WUMPUS IS IN ROOM " + this.gl.getCave().convertToIndex(this.gl.getWumpus().getPos());
        }
        else if(ran < 0.55){
            int rowDist = Math.abs(this.gl.getWumpus().getPos()[0] - this.gl.getPlayer().getPosition()[0]);
            int colDist = Math.abs(this.gl.getWumpus().getPos()[0] - this.gl.getPlayer().getPosition()[0]);

            if(rowDist <= 2 && colDist <= 2){
                secret = "THE WUMPUS IS WITHIN 2 ROOMS OF YOU";
            }
            else{
                secret = "THE WUMPUS IS NOT WITHIN 2 ROOMS OF YOU";
            }
        }
        else{

            secret = "A LOCATION OF A HAZARD IS AT ROOM " + this.gl.getCave().convertToIndex(this.gl.getPosOfRandomHazard());
        }

        this.gui.displayMessage(secret, "SecretMessage.png");
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

    public void gameOver(boolean win){
         System.out.println("GAME ENDED");
        this.mainState = "end";
        this.gl.getPlayer().calculateScore(this.gl.getWumpus().wumpusAlive());

        this.hs.updateHighScoreValueIfNewHighScore();
        // Debugging statements
        System.out.println("Player Score: " + this.gl.getPlayer().getScore());
        System.out.println("Updating High Scores");

        this.hs.updateHighScoreValueIfNewHighScore();

        // Ensure to call only the update method to write scores
        this.hs.updateAllHighScores();

        // Optional: Print high scores to verify
        this.hs.printHighScore();

        this.gui.displayEndPanel(this.hs.getAllHighScores(), this.gl.getPlayer(), win);
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
