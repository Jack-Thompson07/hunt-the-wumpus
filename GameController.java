public class GameController {

    private GameLocations gl;
    private HighScore hs;
    private Gui gui;
    private Trivia trivia;
    private AudioManager audioManager;

    private String mainState;

    private String[] currentQuestion;
    private int numCorrect;
    private int numRequired;
    private int numLeft;


    public GameController() {

        this.audioManager = new AudioManager();
        this.trivia = new Trivia();
        this.hs = new HighScore();

        this.gui = new Gui(this);
        this.mainState = "start";
        this.gui.displayStartPanel(this.hs.getAllHighScores());
        this.audioManager.playMenuMusic();
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
        
            this.gui.updateMapPanel();
            this.gl.getWumpus().newTurn();
            checkNearby();
        

    }

    public void checkRoom() {
        if (gl.getWumpus().getPos()[0] == gl.getPlayer().getPosition()[0]
                && gl.getWumpus().getPos()[1] == gl.getPlayer().getPosition()[1]) {
            this.mainState = "wumpus";
            System.out.println("wumpus");
            this.gui.displayMessage(
                    "<html>You hear a loud roaring and look up<br>You are meet face to face with a horrifying monster<br>You draw your sword and prepare to fight<br>YOU RAN INTO THE WUMPUS<br>YOU MUST ANSWER 3 OUT OF 5 TRIVIA QUESTIONS TO SURVIVE</html>",
                    "WumpusImage.png");
            this.audioManager.wumpusRoar();
        } else if (gl.getChestAt(gl.getPlayer().getPosition())) {
            int coins = gl.openChest(gl.getPlayer().getPosition());
            gui.displayMessage("<html>You see something bright comming from the corner of the room<br>You walk over and find a large glowing chest<br>You decide to open in hoping it will have something of value<br>YOU FOUND A CHEST<br>YOU GET " + coins + " COINS</html>", "ChestImage.png");
            gl.getPlayer().addCoins(coins);
        } else if (gl.getHazardAt(this.gl.getPlayer().getPosition()) != null) {
            if (gl.getHazardAt(this.gl.getPlayer().getPosition()).equals("bat")) {
                System.out.println("Bat");
                this.gui.displayMessage(
                        "<html>You walk into the room and hear the sound of fluttering wings.<br>You look up and see hundreds of bats flying tward you.<br>They pick you up and carry you away while you struggle to fight them off.<br><br>YOU RAN INTO BATS!<br>THEY WILL CARRY YOU TO A NEW RANDOM ROOM!</html>",
                        "BatsImage.png");
                this.audioManager.playBatSound();
                batCarry();
            }

            else {
                System.out.println("Pit");
                this.mainState = "pit";
                this.gui.displayMessage(
                        "<html>You walk into the room an feel a weightless sensation.<br>You look down and see nothing below your feet.<br>You quickly grab on to the ledge struggling to hold on.<br><br>YOU RAN INTO A BOTTOMLESS PIT!<br>YOU MUST ANSWER 2 OUT OF 3 TRIVIA QUESTIONS TO SURVIVE!</html>",
                        "PitImage.png");

            }
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
            this.audioManager.stopMusic();
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
                this.mainState = "game over";
                this.gui.displayMessage("<html>YOU HEAR A LOWD ROARING AND THEN A CRASHING THUD<br>YOUR ARROW HIT ITS MARK AND YOU KILLED THE WUMOUS</html>", "VictoryImage.png");
            }
            else{
                this.gl.getWumpus().arrowMissed();
                this.gui.displayMessage("<html>You hear a large stomping of feet in the distance<br>It is getting further and further away<br>YOUR ARROW MISSED THE WUMPUS<br>IT RAN AWAY</html>","VictoryImage.png");
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
                this.gui.displayMessage("YOU MUST ANSWER 2 OUT OF 3 QUESTIONS CORRECT TO GET A SECRET", "SecretImage.png");
            }
        }
        if(action.equals("continue")){
            System.out.println("Continued");
            this.audioManager.stopMusic();
            if(mainState.equals("map")){
                this.gui.displayMapPanel(this.gl.getPlayer());
            }
            if(this.mainState.equals("arrow shot")){
                this.mainState = "map";
                this.gl.getPlayer().addTurn();
                updateGame();
            }
            if(mainState.equals("game over")){
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
                this.audioManager.stopMusic();
                this.audioManager.wumpusFightMusic();
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
                this.audioManager.stopMusic();
                if(numCorrect >= numRequired){
                    
                    if(this.mainState.equals("pit")){
                        this.gui.displayMessage("<html>You take a deep breath and give one last effort to pull yourself up.<br>You are successful and are now out of the pit.<br>CONGRATULATIONS!<br>YOU SURVIVED THE HAZARD!</html>", "VictoryImage.png");
                    }
                    if(this.mainState.equals("wumpus")){
                        this.gl.getWumpus().defeated();
                        this.gui.displayMessage("<html>You swing your sword and hit the wumpus's foot<br>It was not enough to pierce its thick skin but it did scare if off<br>THE WUMPUS RAN AWAY<br>YOU SURVIVED THE WUMPUS</html>","VictoryImage.png");
                    }
                    if(this.mainState.equals("buy arrow")){
                        this.gl.getPlayer().addArrows(2);
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
                        this.audioManager.playFallingPitSound();
                        this.mainState = "game over";
                    }
                    if(this.mainState.equals("wumpus")){
                        
                        this.gui.displayMessage("<html>You swung your sword and missed<br>This allowed the wumpus to have an open attack on you<br>It stomped on you and crushed you under its immense weight<br>YOU DIED TO THE WUMPUS</html>", "LooseImage.png");
                        this.audioManager.wumpusStomp();
                        this.mainState = "game over";

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
        String secret = "<html>HERE IS YOUR SECRET:<br>";
        double ran = Math.random();
        if(ran < 0.2){
            secret += "THE WUMPUS IS IN ROOM " + this.gl.getCave().convertToIndex(this.gl.getWumpus().getPos());
        }
        else if(ran < 0.55){
            int rowDist = Math.abs(this.gl.getWumpus().getPos()[0] - this.gl.getPlayer().getPosition()[0]);
            int colDist = Math.abs(this.gl.getWumpus().getPos()[0] - this.gl.getPlayer().getPosition()[0]);

            if(rowDist <= 2 && colDist <= 2){
                secret += "THE WUMPUS IS WITHIN 2 ROOMS OF YOU";
            }
            else{
                secret += "THE WUMPUS IS NOT WITHIN 2 ROOMS OF YOU";
            }
        }
        else{

            secret += "A LOCATION OF A HAZARD IS AT ROOM " + this.gl.getCave().convertToIndex(this.gl.getPosOfRandomHazard());
        }
        secret += "</html>";
        this.gui.displayMessage(secret, "SecretImage.png");
    }

    //return true if all answered the required amount correctly

    public void batCarry(){
        int[] cords = {(int)(Math.random() * 5),(int)(Math.random() * 6)};
        this.gl.getPlayer().move(cords);
        this.gui.updateMapPanel();
        checkRoom();
    }

    public void answerQuestion(String answer){
        if(this.trivia.isCorrectAnswer(this.currentQuestion[0],answer)){
            this.audioManager.triviaCorrect();
            this.numCorrect ++;
        }
        else
            this.audioManager.triviaIncorrect();
            
        doAction("question");
    }


    public void gameOver(boolean win){
        if(!this.gl.getWumpus().getAlive())
            this.audioManager.gameEnd();
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
