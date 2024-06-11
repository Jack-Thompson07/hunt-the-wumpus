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

public class Gui extends JFrame {

    //////////////
    // Properties
    //////////////
    private GameController gc;
    private MapPanel mp;
    private EndPanel ep;
    private StartPanel sp;
    private ShootArrowPanel sa;

    //////////////
    // Constructors
    //////////////
    public Gui(GameController gc) {
        this.gc = gc;

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

    public void displayMapPanel(Player p){
        wipe();

        this.mp = new MapPanel(gc,p);
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

    public void displayQuestion(String[] currentQuestion){
        wipe();
        add(new Question(currentQuestion, this.gc));
        revalidate();
        repaint();
    }

    public void displayEndPanel(Player[] highScorePlayers, Player currentPlayer, boolean win){
        wipe();
        this.ep = new EndPanel(highScorePlayers, currentPlayer, win);
        add(ep);
        revalidate();
        repaint();

    }

    public void displayStartPanel(Player[] highScorePlayers){
        wipe();
        this.sp = new StartPanel(highScorePlayers, this.gc);
        add(sp);
        revalidate();
        repaint();
    }

    public String getStartText(){
        return this.sp.getTextFieldValue();
    }

    public void displayShootArrowPanel(int[] sides){
        wipe();
        this.sa = new ShootArrowPanel(sides, this.gc);
        add(sa);
        revalidate();
        repaint();
    }

    public class MapPanel extends JPanel {
        private GameController gc;
        private int turn;
        private int coins;
        private int arrows;
        private Map map;
        private int fontSize;

        public MapPanel(GameController gc, Player p) {
            this.gc = gc;
            this.turn = p.getTurn();
            this.coins = p.getCoins();
            this.arrows = p.getArrows();
            this.fontSize = 24;

            this.map = new Map(gc);
            setLayout(new BorderLayout());

            // Top panel to display coins, turn, and arrows
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new GridLayout(1, 3)); // 1 row, 3 columns
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around top panel

            Font labelFont = new Font("Serif", Font.PLAIN, fontSize);
            JLabel turnLabel = new JLabel("Turn: " + turn);
            turnLabel.setFont(labelFont);
            JLabel coinsLabel = new JLabel("Coins: " + coins);
            coinsLabel.setFont(labelFont);
            JLabel arrowsLabel = new JLabel("Arrows: " + arrows);
            arrowsLabel.setFont(labelFont);

            topPanel.add(turnLabel);
            topPanel.add(coinsLabel);
            topPanel.add(arrowsLabel);

            // Bottom panel to hold the map and buttons
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around bottom panel

            // Map panel (2/3 of the width)
            JPanel mapPanel = new JPanel();
            mapPanel.setLayout(new BorderLayout());
            mapPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around map panel
            mapPanel.add(this.map, BorderLayout.CENTER);

            // Buttons panel (1/3 of the width)
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, with gaps

            Button button = new Button("Shoot Arrow", this.gc, "shoot arrow");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            button = new Button("Buy Arrow", this.gc, "buy arrow");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            button = new Button("Buy Secret", this.gc, "buy secret");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            bottomPanel.add(mapPanel, BorderLayout.CENTER); // Map panel takes the center (2/3 width)
            bottomPanel.add(buttonsPanel, BorderLayout.EAST); // Buttons panel takes the right side (1/3 width)

            add(topPanel, BorderLayout.NORTH); // Top panel at the top (1/5 of the height)
            add(bottomPanel, BorderLayout.CENTER); // Bottom panel in the center (4/5 of the height)
        }

        public void update() {
            removeAll();

            // Re-create the top panel to display updated coins, turn, and arrows
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new GridLayout(1, 3));
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around top panel

            Font labelFont = new Font("Serif", Font.PLAIN, fontSize);
            JLabel turnLabel = new JLabel("Turn: " + turn);
            turnLabel.setFont(labelFont);
            JLabel coinsLabel = new JLabel("Coins: " + coins);
            coinsLabel.setFont(labelFont);
            JLabel arrowsLabel = new JLabel("Arrows: " + arrows);
            arrowsLabel.setFont(labelFont);

            topPanel.add(turnLabel);
            topPanel.add(coinsLabel);
            topPanel.add(arrowsLabel);

            // Re-create the map
            this.map = new Map(this.gc);
            JPanel mapPanel = new JPanel(new BorderLayout());
            mapPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around map panel
            mapPanel.add(this.map, BorderLayout.CENTER);

            // Re-create the buttons panel
            JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            Button button = new Button("Shoot Arrow", this.gc, "shoot arrow");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            button = new Button("Buy Arrow", this.gc, "buy arrow");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            button = new Button("Buy Secret", this.gc, "buy secret");
            button.setFont(new Font("Serif", Font.BOLD, 20)); // Large font
            button.setBorder(BorderFactory.createBevelBorder(1)); // Beveled border
            buttonsPanel.add(button);

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add space around bottom panel
            bottomPanel.add(mapPanel, BorderLayout.CENTER);
            bottomPanel.add(buttonsPanel, BorderLayout.EAST);

            add(topPanel, BorderLayout.NORTH);
            add(bottomPanel, BorderLayout.CENTER);

            revalidate();
            repaint();
            System.out.println("repainted");
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
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

            ImageIcon image = new ImageIcon(imagePath);
            JLabel imageLabel = new JLabel(image);
            imageLabel.setFont(new Font(null, Font.BOLD, 20));
            JLabel text = new JLabel(message);
            text.setFont(new Font("Serif", Font.BOLD, 22));
            Button b = new Button("CONTINUE", this.gc, "continue");

            add(imageLabel);
            add(Box.createRigidArea(new Dimension(0,20)));
            add(text);
            add(Box.createRigidArea(new Dimension(0,20)));
            add(b);
        }
    }

    public class Question extends JPanel {
        GameController gc;

        public Question(String[] currentQuestion, GameController gc) {
            this.gc = gc;
            setLayout(new BorderLayout());

            // Create and configure the question label
            JLabel text = new JLabel(currentQuestion[0], SwingConstants.CENTER);
            text.setFont(new Font("Serif", Font.BOLD, 24)); // Set the font size and style
            add(text, BorderLayout.NORTH); // Add the question label to the top center

            // Create a panel for the buttons and set its layout to a 2x2 grid
            JPanel buttonsPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2x2 grid with some spacing

            // Add the buttons to the grid panel
            for (int i = 1; i < currentQuestion.length; i++) {
                JButton button = new TriviaButton(currentQuestion[i], this.gc);
                button.setFont(new Font("Serif", Font.PLAIN, 18)); // Set the font size for the buttons
                buttonsPanel.add(button);
            }

            add(buttonsPanel, BorderLayout.CENTER); // Add the buttons panel to the center
        }
    }

    public class EndPanel extends JPanel {
        public EndPanel(Player[] highScorePlayers, Player currentPlayer, boolean win) {
            setLayout(new BorderLayout());

            // Create the left panel for the result and current player's information
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setPreferredSize(new Dimension(400, 600));  

            JLabel resultLabel = new JLabel(win ? "You Win!" : "You Lose!");
            resultLabel.setFont(new Font("Serif", Font.BOLD, 60));
            resultLabel.setAlignmentX(CENTER_ALIGNMENT);
            if (win) {
                resultLabel.setForeground(Color.GREEN);
            } else {
                resultLabel.setForeground(Color.GRAY);
            }
            leftPanel.add(resultLabel);

            JLabel nameLabel = new JLabel("Player: " + currentPlayer.getName());
            nameLabel.setFont(new Font("Serif", Font.PLAIN, 42));
            nameLabel.setAlignmentX(CENTER_ALIGNMENT);

            JLabel scoreLabel = new JLabel("Score: " + currentPlayer.getScore());
            scoreLabel.setFont(new Font("Serif", Font.PLAIN, 42));
            scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

            leftPanel.add(nameLabel);
            leftPanel.add(scoreLabel);

            // Create the right panel for the high score leaderboard
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setPreferredSize(new Dimension(400, 800));

            JLabel highScoreLabel = new JLabel("High Scores");
            highScoreLabel.setFont(new Font("Serif", Font.BOLD, 42));
            highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
            rightPanel.add(highScoreLabel);

            // Remove null players and sort highScorePlayers by score in descending order
            highScorePlayers = java.util.Arrays.stream(highScorePlayers)
                    .filter(player -> player != null)
                    .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                    .toArray(Player[]::new);

            for (int i = 0; i < highScorePlayers.length && i < 10; i++) {
                Player player = highScorePlayers[i];
                JLabel playerScoreLabel = new JLabel((i + 1) + ". " + player.getName() + ": " + player.getScore());
                playerScoreLabel.setFont(new Font("Serif", Font.PLAIN, 35));
                playerScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
                rightPanel.add(playerScoreLabel);
            }

            add(leftPanel, BorderLayout.WEST);
            add(rightPanel, BorderLayout.EAST);

            if (win) {
                setBackground(Color.YELLOW);
            } else {
                setBackground(Color.DARK_GRAY);
            }
        }
    }

    public class StartPanel extends JPanel {
        private JTextField textField;
        private GameController gc;

        public StartPanel(Player[] highScorePlayers, GameController gc) {
            this.gc = gc;
            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            JPanel top = new JPanel(new FlowLayout());
            JPanel bottom = new JPanel(new BorderLayout());
            // Create the left panel for the title, text box, and start button
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            JLabel titleLabel = new JLabel("HUNT THE WUMPUS");
            titleLabel.setAlignmentX(CENTER_ALIGNMENT);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
            top.add(titleLabel);

            leftPanel.setPreferredSize(new Dimension(400, 600)); 

            JLabel nameLabel = new JLabel("ENTER YOUR NAME");
            
            nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
            nameLabel.setAlignmentX(CENTER_ALIGNMENT);
            leftPanel.add(nameLabel);

            textField = new JTextField();
            textField.setMaximumSize(new Dimension(400, 35));
            leftPanel.add(textField);

            Button startButton = new Button("Start",this.gc,"start");
            startButton.setAlignmentX(CENTER_ALIGNMENT);
            leftPanel.add(startButton);

            // Create the right panel for the high score leaderboard
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setPreferredSize(new Dimension(400, 800));

            JLabel highScoreLabel = new JLabel("High Scores");
            highScoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
            highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
            rightPanel.add(highScoreLabel);

            // Remove null players and sort highScorePlayers by score in descending order
            highScorePlayers = java.util.Arrays.stream(highScorePlayers)
                    .filter(player -> player != null)
                    .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
                    .toArray(Player[]::new);

            for (int i = 0; i < highScorePlayers.length && i < 10; i++) {
                Player player = highScorePlayers[i];
                JLabel playerScoreLabel = new JLabel((i + 1) + ". " + player.getName() + ": " + player.getScore());
                playerScoreLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                playerScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
                rightPanel.add(playerScoreLabel);
            }

            bottom.add(leftPanel, BorderLayout.WEST);
            bottom.add(rightPanel, BorderLayout.EAST);

            add(top);
            add(bottom);

            setBackground(Color.LIGHT_GRAY);
        }

        public String getTextFieldValue() {
            return textField.getText();
        }
    }

    public class ShootArrowPanel extends JPanel {
        private GameController gc;

        public ShootArrowPanel(int[] directions, GameController gc) {
            this.gc = gc;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Which Direction would you like to shoot the arrow", JLabel.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 35));
            add(label);


            JPanel arrowButtons = new JPanel();
            arrowButtons.setLayout(new BoxLayout(arrowButtons, BoxLayout.X_AXIS));

            for(int i : directions){
                if(i != -1){
                    ArrowButton b = new ArrowButton(i, gc);
                    b.setFont(new Font(null, Font.BOLD, 100));
                    arrowButtons.add(b);
                }
            }
            add(arrowButtons);
            Button backButton = new Button("BACK", this.gc, "back");
            add(backButton);
        }
    }

}
