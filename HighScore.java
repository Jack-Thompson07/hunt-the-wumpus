public class HighScore extends Gui{

    ///////////////////////////////////////
    //Properties
    ///////////////////////////////////////
    int moves;
    int goldCoinsLeft;
    int arrows;
    int deadWumpus = 0;


    ////////////////////////////////////////
    //Constructor
    ////////////////////////////////////////
    public HighScore(){

    }


    ////////////////////////////////////////
    //Methods
    ////////////////////////////////////////

    public int getNumOfMoves(){
        //need to get the number of moves from game controller and set it to Moves
        //GameController.addMove();
        return 0;
    }


    public int calculateHighScore(){
    /*
    Need to use this way of scoring:
    100 pints - N + G + (5 * A) + W
    N = num of turns
    G = num of Gold Coins left
    A = num of Arrows
    W = 50 if you kill Wumpus otherwise 0
    
    */
        return 0;
    }

    public boolean isWumpusDead(){
        //Read method from Game Control to see if wumpus is dead so I can add to score
        //if GameController.checkWampusAlive() == true
        //add 50 to deadWumpus
        //otherwise keep it at 0
        return false;
    }

    public void writeScoreToFile(){
        //Need to create a CSV File to write you scores to so you can pick it up when you want
        //Gonna need to tie it to the player
    }

    public void getScoreFromFile(){
        //When the player wants to play they should be able to retrive
        //their data from previous game when they log in
    }

}