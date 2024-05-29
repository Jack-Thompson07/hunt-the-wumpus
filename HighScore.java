/* 
 * Change Array to string that caps at 10
 * Calculate score goes to Player
 * In Constructor have it enter a player as a perameter: DO a player.getname() for example
 * 
 * What I need to do:
 * Write High Score to File
 * Wrtie the Name and UUID to File
  */



import java.io.Console;
import java.io.File;
import java.util.Scanner;
import java.util.UUID;
import java.io.FileWriter;

public class HighScore {
    ///////////////////////////////////////
    //Properties
    ///////////////////////////////////////

    public static Player[] AllHighScores = new Player[10];
    public static int HighScoreCount = 0;
    public static final int MAXHIGHSCORECOUNT = 10;
    private Player player;


    ////////////////////////////////////////
    //Constructor
    ////////////////////////////////////////
    // public HighScore(String newUUID, String name, int scoreValue){
    //     this.uuid = newUUID;
    //     this.score = scoreValue;

    //     fillAllHighScores();
    // }

    //  public HighScore(String newUUID, String[] split) {
    //     //TODO Auto-generated constructor stub
    // }

    public HighScore(Player player){
        this.player = player;

        updateHighScoreValueIfNewHighScore();
    }


    ////////////////////////////////////////
    //Methods
    ////////////////////////////////////////

    public void updateHighScoreValueIfNewHighScore() {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].getUUID().equals(this.player.getUUID())) {
                if(AllHighScores[i].getScore() < this.player.getScore()) {
                    AllHighScores[i].setScore(this.player.getScore());
                }
            }
        }

        // We didn't find a player, so create a new one
        if (HighScoreCount < MAXHIGHSCORECOUNT) {
            AllHighScores[HighScoreCount] = this.player;
        } else {
            System.out.println("We have reached the max limit on storing HighScores for players, no more HighScores can be added.");
        }

        updateAllHighScores();

        //return null;
    }


    public int getHighScoreValue() {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].getUUID().equals(this.player.getUUID())) {
                return AllHighScores[i].getScore();
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
                    String[] sections = reader.nextLine().split(",");
                    Player newPlayer = new Player(UUID.fromString(sections[0]), sections[1]);
                    newPlayer.setScore(Integer.parseInt(sections[2]));
                    AllHighScores[HighScoreCount] = newPlayer;
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
            System.out.println(e.toString());
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

        AllHighScores = new Player[10];
        HighScoreCount = 0;
    }


    public String toString() {
        return this.player.getUUID().toString() + "," + this.player.getName() + "," + this.player.getScore();  
    }

    public void printHighScore(){
        //Print the high score to the console
        for(int i = 0; i < AllHighScores.length; ++i) {
            System.out.println(AllHighScores[i].getUUID().toString() + "," + AllHighScores[i].getName() + "," + AllHighScores[i].getScore());
        }
    }

}