import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Player {
    private String uuid;
    private String name;
    private int highScore;
    private int moves;
    private int goldCoins;
    private int arrows;
    private int[] position;
    private boolean alive;
    private static final String STATS_FILE = "player_stats.csv";

    // Constructor initializing player with starting position and name
    public Player(String name, int[] startPosition) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.highScore = 0;
        this.moves = 0;
        this.goldCoins = 0;
        this.arrows = 3; // Example starting with 3 arrows
        this.position = startPosition;
        this.alive = true;
        
    }

    // Method to move the player
    public void move(int[] newPosition) {
        this.position = newPosition;
    }

    // Method to increment the move count
    public void addMove() {
        this.moves++;
    }

    // Method to get the player's position
    public int[] getPosition() {
        return this.position;
    }

    // Method to update player statistics
    public void updateStats(int gold, int arrows) {
        this.goldCoins = gold;
        this.arrows = arrows;
    }

    // Method to set the player's high score
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    
    // Method to return the UUID
    public String getUuid() {
        return this.uuid;
    }

    // Method to return the name
    public String getName() {
        return this.name;
    }

    // Method to return the high score
    public int getHighScore() {
        return this.highScore;
    }

    // Method to return the number of moves
    public int getMoves() {
        return this.moves;
    }

    // Method to return the number of gold coins
    public int getGoldCoins() {
        return this.goldCoins;
    }

    // Method to return the number of arrows
    public int getArrows() {
        return this.arrows;
    }

    public void die(){
        this.alive = false;
    }

    
    public int calculateScore(int numTurns, int numGoldCoinsLeft, int numArrowsLeft, Boolean isWumpusDead){
        /*
            Need to use this way of scoring:
            100 pints - N + G + (5 * A) + W
            N = num of turns
            G = num of Gold Coins left
            A = num of Arrows
            W = 50 if you kill Wumpus otherwise 0    
        */

        int newScore = 100 - numTurns + numGoldCoinsLeft + (5 * numArrowsLeft);
        if(isWumpusDead) {
            newScore = newScore + 50;
        }
               
        
        
        return newScore;
    }

}