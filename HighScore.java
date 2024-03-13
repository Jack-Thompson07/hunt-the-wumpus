public class HighScore extends GUI{
    ///////////////////////////////////////
    //Properties
    ///////////////////////////////////////

    public static HighScore[] AllHighScores = new HighScore[10];
    public static int HighScoreCount = 0;
    public static final int MAXHIGHSCORECOUNT = 10;
    private String uuid;
    private String score;


    ////////////////////////////////////////
    //Constructor
    ////////////////////////////////////////
    public HighScore(String newUUID, int newScore){
        this.uuid = newUUID;
        this.score = newScore;
    }


    ////////////////////////////////////////
    //Methods
    ////////////////////////////////////////

    public String get_UUID(){
        return this.uuid;
    }

    public String get_Score(){
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
       
        this.score = newScore;

        newScore;
    }


    public HighScore getHighScore() {
        for (int i = 0; i < HighScoreCount; i++) {
            if (AllHighScores[i].get_UUID().equals(this.uuid)) {
                return AllHighScores[i];
            }
        }

        // We didn't find a player, so create a new one
        if (HighScoreCount < MAXHIGHSCORECOUNT) {
            HightScore newHighScore = new HightScore(uuid, Integer.MIN_VALUE);
            AllHighScores[HighScoreCount] = newHighScore;
            return newHighScore;

        } else {
            System.out.println("We have reached the max limit on storing HighScores for players, no more HighScores can be added.");
        }

        return null;
    }

    public static void fillAllHighScores() {
    
        try {
            File f = new File("high_score_data.csv");
            Scanner reader = new Scanner(f);
            reader.nextLine();
            while (reader.hasNext()) {
                AllHighScores[HighScoreCount] = new HightScore(reader.nextLine().split(","));
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

    // Updates the players results back to the file
    public static void updateAllHighScores() {
        try {
            FileWriter writer = new FileWriter(new File("high_score_data.csv"), false);

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

    public String toString() {
        return this.uuid + "," + this.score;  
    }

}