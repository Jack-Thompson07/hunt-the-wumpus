import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.UUID;

public class HighScore {
    ///////////////////////////////////////
    // Properties
    ///////////////////////////////////////

    public Player[] AllHighScores = new Player[10];
    public int HighScoreCount = 0;
    public final int MAXHIGHSCORECOUNT = 10;
    private Player player;

    ////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////

    public HighScore() {
        fillAllHighScores();
    }

    ////////////////////////////////////////
    // Methods
    ////////////////////////////////////////


    public void addMainPlayer(Player p){
        this.player = p;
        updateHighScoreValueIfNewHighScore();
    }
    public void updateHighScoreValueIfNewHighScore() {
        boolean playerExists = false;
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].getUUID().equals(this.player.getUUID())) {
                playerExists = true;
                if (AllHighScores[i].getScore() < this.player.getScore()) {
                    AllHighScores[i].setScore(this.player.getScore());
                }
                break;
            }
        }

        // We didn't find a player, so create a new one
        if (!playerExists) {
            if (HighScoreCount < MAXHIGHSCORECOUNT) {
                AllHighScores[HighScoreCount] = this.player;
                HighScoreCount++;
            } else {
                System.out.println("We have reached the max limit on storing HighScores for players, no more HighScores can be added.");
            }
        }

        // Sort the high scores by score in descending order
        sortHighScoresByScore();

        updateAllHighScores();
    }

    private void sortHighScoresByScore() {
        // Bubble sort algorithm
        for (int i = 0; i < HighScoreCount - 1; i++) {
            for (int j = 0; j < HighScoreCount - i - 1; j++) {
                if (AllHighScores[j].getScore() < AllHighScores[j + 1].getScore()) {
                    // Swap AllHighScores[j] and AllHighScores[j+1]
                    Player temp = AllHighScores[j];
                    AllHighScores[j] = AllHighScores[j + 1];
                    AllHighScores[j + 1] = temp;
                }
            }
        }
    }

    public int getHighScoreValue() {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].getUUID().equals(this.player.getUUID())) {
                return AllHighScores[i].getScore();
            }
        }
        return -1;
    }

    public void fillAllHighScores() {
        if (HighScoreCount == 0) {
            try {
                File f = new File("player_stats.csv");
                Scanner reader = new Scanner(f);
                reader.nextLine(); // Skip header
                while (reader.hasNext()) {
                    String[] sections = reader.nextLine().split(",");
                    Player newPlayer = new Player(UUID.fromString(sections[0]), sections[1]);
                    newPlayer.setScore(Integer.parseInt(sections[2]));
                    AllHighScores[HighScoreCount] = newPlayer;
                    if (HighScoreCount < MAXHIGHSCORECOUNT) {
                        HighScoreCount++;
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
    public void updateAllHighScores() {
        try {
            FileWriter writer = new FileWriter(new File("player_stats.csv"), false);
            writer.write("uuid,name,score\n"); // Write header
            for (int i = 0; i < HighScoreCount; i++) {
                if (AllHighScores[i] != null) {
                    writer.write(AllHighScores[i].toString() + "\n");
                } else {
                    break;
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void deleteAllHighScores() {
        try {
            FileWriter writer = new FileWriter(new File("player_stats.csv"), false);
            // Write the header only to reset the file
            writer.write("uuid,name,score\n");
            writer.close();
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        AllHighScores = new Player[10];
        HighScoreCount = 0;
    }

    public void printHighScore() {
        // Print the high score to the console
        for (int i = 0; i < HighScoreCount; i++) {
            System.out.println(AllHighScores[i].toString());
        }
    }

    public Player[] getAllHighScores(){
        return this.AllHighScores;
    }
}