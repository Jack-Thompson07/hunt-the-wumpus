// Graphical INterface object Displays the state of the 
// current game (current room, connected trooms. Inventory. etc)
// Class representing the pentagon. 
// in charge of rendering everything
// how am I gonna display the player in the cave and the cave itself
// How to diplay the menu
// Basically anything that the user can interact with I need to consider. 

//Gui tells game control where player wants to move and game control says if its a valid move or not
//Have the caves be hexagon buttons that you can click on so it travels you there

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JButton;

public class Gui extends JFrame {

    //////////////
    // Properties
    //////////////
    private GameController gc;
    private ArrayList<JButton> buttons;

    //////////////
    // Constructors
    //////////////
    public Gui(GameController gc) {
        this.gc = gc;
        this.buttons = new ArrayList<JButton>();

        setTitle("MAIN MENU");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize(1000, 1000);
        setLayout(new FlowLayout());

        

        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //////////////
    // Methods
    //////////////

    public void run() {
    }

    public void map(){
        setTitle("MAP");
    }

    public void mainMenu(){
        Icon start = new ImageIcon("StartGameButton.png");
        Button startButton = new Button("START", this.gc, "start game");

        this.buttons.add(startButton);
        add(startButton);
    }

    public void wipe(){
        for(JButton b : this.buttons){
            remove(b);
        }
        repaint();
    }
}