
public class GameLocations {
  ////////////////////////
  // Properties
  ////////////////////////

    private Player player;

    private Cave cave;

    private String[][] hazards;
    private Boolean[][] chests;

    private Wumpus wumpus;

    ////////////////////////
    // Constructor
    ////////////////////////
    public GameLocations(String name) {
        this.wumpus = new Wumpus(this);
        this.cave = new Cave();
        placeHazards();
        placeChests();
        placePlayer(name);
    }
  ////////////////////////
  // Methods
  ////////////////////////
    public Cave getCave(){
        return this.cave;
    }

    public int openChest(int[] cords){
        this.chests[cords[0]][cords[1]] = false;
        return (int)(Math.random() * 4) + 2;
    }

    public boolean getChestAt(int[] cords){
        return this.chests[cords[0]][cords[1]];
    }

    public String getHazardAt(int[] cords){
        return this.hazards[cords[0]][cords[1]];
    }

    public Player getPlayer(){
        return this.player;
    }

    public void placeHazards(){
        this.hazards = new String[5][6];

        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }

            this.hazards[row][col] = "bat";
        }


        for(int i = 0; i < 2; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null)){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }
            this.hazards[row][col] = "pit";
        }
    }

    public void placeChests(){
        this.chests = new Boolean[][]{
            {false, false, false, false, false,false},
            {false, false, false, false, false,false},
            {false, false, false, false, false,false},
            {false, false, false, false, false,false},
            {false, false, false, false, false,false}
        };

        for(int i = 0; i < 7; i ++){
            int col = -1;
            int row = -1;
            while((row == -1) || (this.hazards[row][col] != null) || this.chests[row][col]){
                row = (int)(Math.random() * 5);
                col = (int)(Math.random() * 6);
            }

            this.chests[row][col] = true;
            System.out.println(row + "-" + col);
        }
    }

    public void placePlayer(String name){
        int col = -1;
        int row = -1;
        while((row == -1) || (this.hazards[row][col] != null) || (row == this.wumpus.getPos()[0] && col == this.wumpus.getPos()[1]) || (this.chests[row][col])){
            row = (int)(Math.random() * 5);
            col = (int)(Math.random() * 6);
        }
        this.player = new Player(name,new int[]{row,col});
    }

    public Wumpus getWumpus(){
        return this.wumpus;
    }

    public int[] getPosOfRandomHazard(){
        int row = -1;
        int col = -1;

        while(row == -1 || this.hazards[row][col] == null){
            row = (int)(Math.random() * 5);
            col = (int)(Math.random() * 6);
        }

        return new int[]{row,col};
    }




    public class Wumpus{
        private int[] cords;
        private boolean alive;
        private boolean awake;
        private int turnTillSleep;
        private GameLocations gl;

        public Wumpus(GameLocations gl){
            this.gl = gl;
            alive = true;
            cords = new int[]{(int)(Math.random() * 5),(int)(Math.random() * 6)};
            System.out.println(cords[0] + " - " + cords[1]);
        }

        public void die(){
            this.alive = false;
        }

        public int[] getPos(){
            return cords;
        }

        public boolean wumpusAlive(){
            return alive;
        }

        public void arrowMissed(){
            wakeUp();
            move(1);
        }

        public void wakeUp(){
            this.awake = true;
            this.turnTillSleep = 2;
        }

        public void newTurn(){
            if(awake){
                if(Math.random() < 0.35){
                    move(1);
                    this.turnTillSleep = 2;
                }
                else{
                    this.turnTillSleep--;
                }
            } 
            if(this.turnTillSleep == 0){
                this.awake = false;
            }
        }

        public void move(int spaces){
            for(int i = 0; i < spaces; i ++){
                int direction = -1;
                while(direction == -1 || (direction != this.gl.getCave().getTunnels(cords)[0] && direction != this.gl.getCave().getTunnels(cords)[1] && direction != this.gl.getCave().getTunnels(cords)[2])){
                    direction = (int)(Math.random() * 6);
                }
                this.cords = this.gl.getCave().getPosOfTunnel(this.cords, direction);
            }
            System.out.println("wumpus moved");
        }

        public void defeated(){
            wakeUp();
            move(((int)((Math.random())*3)+2)); 
        }
        public boolean getAlive(){
            return this.alive;
        }
    }

}