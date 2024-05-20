import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class HighScore {
    ///////////////////////////////////////
    //Properties
    ///////////////////////////////////////

    public static HighScore[] AllHighScores = new HighScore[10];
    public static int HighScoreCount = 0;
    public static final int MAXHIGHSCORECOUNT = 10;
    private String uuid;
    private int score;
    private int Highscore;


    ////////////////////////////////////////
    //Constructor
    ////////////////////////////////////////
    public HighScore(String newUUID, int scoreValue){
        this.uuid = newUUID;
        this.score = scoreValue;

        fillAllHighScores();
    }

     public HighScore(String newUUID, String[] split) {
        //TODO Auto-generated constructor stub
    }


    ////////////////////////////////////////
    //Methods
    ////////////////////////////////////////

   

    public String get_UUID(){
        return this.uuid;
    }

    public int get_Score(){
        return this.score;
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
               
        updateHighScoreValueIfNewHighScore(newScore);
        
        return newScore;
    }

    public void updateHighScoreValueIfNewHighScore(int highScoreValue) {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].get_UUID().equals(this.uuid)) {
                if(AllHighScores[i].score < highScoreValue) {
                    AllHighScores[i].score = highScoreValue;
                }
            }
        }

        // We didn't find a player, so create a new one
        if (HighScoreCount < MAXHIGHSCORECOUNT) {
            HighScore newHighScore = new HighScore(uuid, highScoreValue);
            AllHighScores[HighScoreCount] = newHighScore;

        } else {
            System.out.println("We have reached the max limit on storing HighScores for players, no more HighScores can be added.");
        }

        updateAllHighScores();

        //return null;
    }


    public int getHighScoreValue() {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].get_UUID().equals(this.uuid)) {
                return AllHighScores[i].Highscore;
            }
        }

        return -1;
    }

    public static void fillAllHighScores() {
        if(HighScoreCount == 0){
            try {
            
                File f = new File("player_stats.csv");
                Scanner reader = new Scanner(f);
                reader.nextLine();
                while (reader.hasNext()) {
                    AllHighScores[HighScoreCount] = new HighScore("hello",reader.nextLine().split(","));
                    if (HighScoreCount < MAXHIGHSCORECOUNT) {
                        HighScoreCount += 1;
                    } else {
                        break;
                    }
                }

                reader.close();
            } catch (Exception ex) {
                System.out.println("Couldn't read the data file to fill the HighScores");
            }
        }
    }

    // Updates the players results back to the file
    public static void updateAllHighScores() {
        try {
            FileWriter writer = new FileWriter(new File("player_stats.csv"), false);

            writer.write("uuid,score\n");
            for (int i = 0; i < AllHighScores.length; i++) {
            if (AllHighScores[i] != null) {
                writer.write(AllHighScores[i] + "\n");
            } else {
                break;
            }
            }

            writer.close();
        } catch (Exception e) {

        }
    }

    public static void deleteAllHighScores() {
        try {
            FileWriter writer = new FileWriter(new File("player_stats.csv"), false);
        
            // Write the header only to reset the file
            writer.write("uuid,score\n");
            writer.close();
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        AllHighScores = new HighScore[10];
        HighScoreCount = 0;
    }


    public String toString() {
        return this.uuid + "," + this.score;  
    }

}