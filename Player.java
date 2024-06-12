
import java.util.UUID;

public class Player {
  ////////////////////////
  // Properties
  ////////////////////////
    private int turn;
    private int[] position;
    private UUID uuid;
    private int score;
    private String name;
    private int coins;
    private int arrows;

    ////////////////////////
    // Constructor
    ////////////////////////
    public Player(String name,int[] cords) {
        this.uuid = UUID.randomUUID();
        this.position = cords;
        this.name = name;
        this.coins = 10;
        this.turn = 1;
        this.score = 0;
        this.arrows = 3;
    }

    public Player(UUID uuid,String name){
        this.uuid = uuid;
        this.name = name;
    }

  ////////////////////////
  // Methods
  ////////////////////////
    public void shootArrow(){
        this.arrows--;
    }

    public void addCoins(int howMany){
        this.coins += howMany;
    }

    public void move(int cords[]) {
        this.position = cords;
    }

    public void addTurn() {
        this.turn++;
    }

    public int getTurn(){
        return this.turn;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void updatePosition(int[] newPos) {
        this.position = newPos;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){     
        return this.score;
    }

    public String getName() {     
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addArrows(int howMany){
        this.arrows += howMany;
    }

    public int getArrows(){
        return this.arrows;
    }

    public int getCoins(){
        return this.coins;
    }

    public int calculateScore(boolean wumpusAlive){
        this.score =  100 - this.turn + this.coins + (this.arrows * 5) + ((!wumpusAlive)? 50 : 0);
        if(score < 0)
            this.score = 0;
        return this.score;
    }

    public String toString() {
        return uuid.toString() + "," + name + "," + score;
    }

    public void takeCoins(int howMany){
        this.coins -= howMany;
    }
}