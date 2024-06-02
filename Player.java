// Laksh
import java.util.UUID;

public class Player {

    private boolean alive;
    private int turn;
    private int[] position;
    private UUID uuid;
    private int score;
    private String name;
    private int coins;
    private int arrows;

    public Player(String name,int[] cords) {
        this.uuid = UUID.randomUUID();
        this.position = cords;
        this.name = name;
        this.coins = 0;
        this.turn = 1;
        this.score = 0;
        this.arrows = 0;
        this.alive = true;
    }

    public Player(UUID uuid,String name){
        this.uuid = uuid;
        this.name = name;
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

    public void die() {
        this.alive = false;
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

    public int calculateScore(boolean wumpusAlive){
        this.score = turn + coins + (5 * arrows) + ((wumpusAlive)? 50 : 0);
        return this.score;
    }

    public String toString() {
        return uuid.toString() + "," + name + "," + score;
    }
}