import java.util.UUID;

public class HighScoreTest{
    ///////////////////////////////////////
    //Properties
    ///////////////////////////////////////

  


    ////////////////////////////////////////
    //Constructor
    ////////////////////////////////////////
    public HighScoreTest(){
        
    }


    ////////////////////////////////////////
    //Methods
    ////////////////////////////////////////
    public void runTests(){
        test_add10PlayersSaveToDisk();
        test_increasePlayerHighScore();
    }

    public void test_add10PlayersSaveToDisk(){

        HighScore.deleteAllHighScores();

        HighScore user1 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user2 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user3 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user4 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user5 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user6 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user7 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user8 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user9 = new HighScore( UUID.randomUUID().toString(), 0);
        HighScore user10 = new HighScore( UUID.randomUUID().toString(), 0);
        // HighScore user11 = new HighScore( UUID.randomUUID().toString(), 0);

        int user1Result = user1.calculateScore( 2,  2,  2,  true);
        if(user1Result != 160 ){
            throw new Exception("Invalid User1 Result");
        }

        HighScore.updateAllHighScores();       

        if( user1.getHighScoreValue() != 160 ) {
            throw new Exception("Invalid User1 Result");
        }

        user2.calculateScore( 5,  3,  8,  true);
        user3.calculateScore( 7,  10,  7,  false);
        user4.calculateScore( 4,  3,  4,  true);
        user5.calculateScore( 9,  4,  6,  false);
        user6.calculateScore( 7,  6,  0,  false);
        user7.calculateScore( 2,  1,  6,  false);
        user8.calculateScore( 4,  0,  9,  true);
        user9.calculateScore( 1,  9,  0,  false);
        user10.calculateScore( 5,  5,  5,  true);
        
        //user11.calculateScore( 6,  2,  9,  false);
      
        
        HighScore.updateAllHighScores();

      

    }

    public void test_increasePlayerHighScore(){

    }

}