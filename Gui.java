// Graphical INterface object Displays the state of the 
// current game (current room, connected trooms. Inventory. etc)
// Class representing the pentagon. 
// in charge of rendering everything
// how am I gonna display the player in the cave and the cave itself
// How to diplay the menu
// Basically anything that the user can interact with I need to consider. 

//Gui tells game control where player wants to move and game control says if its a valid move or not
//Have the caves be hexagon buttons that you can click on so it travels you there

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Gui extends JFrame {

    //////////////
    // Properties
    //////////////
    private GameController gc;
    private ArrayList<JButton> buttons;
    private MapPanel mp;

    //////////////
    // Constructors
    //////////////
    public Gui(GameController gc) {
        this.gc = gc;
        this.buttons = new ArrayList<JButton>();

        setTitle("HUNT THE WAMPUS");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize(1500, 2000);
        setLayout(new FlowLayout());
        
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    

    //////////////
    // Methods
    //////////////

    public void displayMapPanel(){
        wipe();
        
        this.mp = new MapPanel(gc);
        add(mp);

        revalidate();

    }
    
    public void updateMapPanel(){
        this.mp.update();
        
    }

    public void wipe(){
        getContentPane().removeAll();
        revalidate();
    }

    public void displayMessage(String message, String imagePath){
        wipe();
        
        add(new Message(message, imagePath, this.gc));
        revalidate();
        repaint();
        
    }


    public class MapPanel extends JPanel{
        private Map map;
        private GameController gc;
        
        public MapPanel(GameController gc){
            this.gc = gc;
            this.map = new Map(gc);
            setLayout(new GridLayout(2,1));
            JPanel top = new JPanel(new GridLayout(1,2));
            top.add(map);
            add(top);
        }

        public void update(){
            removeAll();
            this.map = new Map(this.gc);
            add(this.map);
            revalidate();
            System.out.println("repained");
        }
    }

    public class Map extends JPanel{

        private static final int HEXAGON_RADIUS = 50; // Adjust the size as needed
        
        public Map(GameController gc){
            setLayout(null); // Using null layout for custom positioning

            int numCols = 6;
            int numRows = 5;
            int buttonDiameter = 2 * HEXAGON_RADIUS;
            double heightStep = Math.sqrt(3) / 2 * buttonDiameter; // Vertical distance between centers
            double widthStep = 1.5 * HEXAGON_RADIUS; // Horizontal distance between centers

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    int x = (int) (col * widthStep);
                    int y = (int) (row * heightStep);
                    if (col % 2 != 0) {
                        y += heightStep / 2; // Offset for every other column
                    }

                    Color color = Color.gray;

                    int[] tunnels = gc.getTunnels(new int[]{row, col});
                    int[] playerTunnels = gc.getTunnels(gc.getPlayerPosition());

                    for(int i : playerTunnels){
                        if(i == -1)
                            break;
                            if((gc.getGameLocations().getCave().getPosOfTunnel(gc.getPlayerPosition(), i)[0] == row) && (gc.getGameLocations().getCave().getPosOfTunnel(gc.getPlayerPosition(), i)[1] == col)){
                            color = color.cyan;
                        }
                    }

                    if((gc.getPlayerPosition()[0] == row) && (gc.getPlayerPosition()[1] == col))
                        color = color.green;


                    HexagonButton button = new HexagonButton(("" + (row * 6 + col + 1)), tunnels, color, HEXAGON_RADIUS, new int[]{row,col}, gc);
                    button.setBounds(x, y, buttonDiameter, buttonDiameter);
                    add(button);
                }
            }

            int panelWidth = (int) (numCols * widthStep + HEXAGON_RADIUS);
            int panelHeight = (int) (numRows * heightStep + HEXAGON_RADIUS);
            setPreferredSize(new Dimension(panelWidth, panelHeight));
        }
    }

    public class Message extends JPanel{
        private GameController gc;
        
        public Message(String message, String imagePath, GameController gc){
            this.gc = gc;
            setLayout(new FlowLayout());
            ImageIcon image = new ImageIcon(imagePath);
            JLabel imageLabel = new JLabel(image);
            JLabel text = new JLabel(message);
            Button b = new Button("CONTIUNUE", this.gc, "continue");

            add(imageLabel);
            add(text);
            add(b);
        }
    }
}


