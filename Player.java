// Laksh
import java.util.UUID;

public class Player {

    private boolean alive = true;
    private int moves;
    private int[] position;
    private UUID uuid;

    public Player(int[] cords) {
        this.position = cords;
        this.uuid = UUID.randomUUID(); // Generating UUID for the player
    }

    public void move(int cords[]) {
        this.position = cords;
    }

    public void addMove() {
        this.moves++;
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
    
}
