// Graphical INterface object Displays the state of the 
// current game (current room, connected trooms. Inventory. etc)
// Class representing the pentagon. 
// in charge of rendering everything
// how am I gonna display the player in the cave and the cave itself
// How to diplay the menu
// Basically anything that the user can interact with I need to consider.

import javax.swing.JFrame;
import java.awt.FlowLayout;

public class Gui extends JFrame{

    private GameController gc = new GameController();


//////////////
// Properties
//////////////

//////////////
// Constructors
//////////////
    public Gui(){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLayout(new FlowLayout());

        

        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }


//////////////
// Methods
//////////////

public void run(){
    while(gc.checkWampusAlive()){

    }
}
}