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


public class Gui extends JFrame{


    //////////////
    // Properties
    //////////////
    private GameController gc;

    //////////////
    // Constructors
    //////////////
    public Gui(){
        this.gc = new GameController();
    }




    //////////////
    // Methods
    //////////////

public void run(){
    while(gc.checkWampusAlive()){
        
    }
}
}
/* 
class HexagonButton extends JButton {
        private static final long serialVersionUID = 1L;
        private static final int SIDES = 6;
        private static final int SIDE_LENGTH = 50;
        public static final int LENGTH = 95;
        public static final int WIDTH = 105;
        private int row = 0;
        private int col = 0;

        public HexagonButton(int row, int col) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            this.row = row;
            this.col = col;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
            }       
            g.drawPolygon(hex);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}*/