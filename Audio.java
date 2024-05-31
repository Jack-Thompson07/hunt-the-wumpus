import javax.sound.sampled.*;
import java.io.File;
import java.util.Scanner;
public class Audio {
    
    ////////////
    // Properties
    ////////////

    ////////////
    // Constructors
    ////////////
    public Audio(){

        Scanner scanner = new Scanner(System.in);
        File file = new File(/* Make it the name of whatever file */);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioSteam);

        String response = "";

      
        response = response.toUpperCase();
        while(!response.equals("Q")){
            System.out.println("P = Play, S = Stop, R = Reset, Q = Quit");
            System.out.println("Enter your choice: ");

            response = scanner.next();
            response = response.toUpperCase();

            switch(response){
            case("P"): clip.start();
            break;
            case("S"): clip.stop();
            break;
            case("R"): clip.setMicrosecondPosition(0);
            break;
            case("Q"): clip.close();
            default:System.out.println("Not a valid response");
            }

            

        }
          clip.start();
       

    }
    /////////////
    // Methods
    /////////////

    public void bat(Clip clip ){
        clip.start(/* The bat sound file */);

    }

    public void pit(Clip clip){
         clip.start(/* The pit sound file */);

         // add trivia music???
    }

    public void wumpus(Clip clip){
             clip.start(/* The wumpus sound file */);
             // try adding intense music type stuff
    }

    public void caveMove(Clip clip){
         clip.start(/* Button clicking sound. Tetris move sound effect?*/);
    }
}

