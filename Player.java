import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Player {

    private boolean alive = true;
    private int moves;
    private int[] position;
    private UUID uuid;
    private HighScore highScore;

    public Player(int[] cords) {
        this.position = cords;
        this.uuid = UUID.randomUUID(); // Generating UUID for the player
        this.highScore = new HighScore();
    }

    public void move(int x, int y) {
        // Implement move logic here
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

    public HighScore getHighScore() {
        return this.highScore;
    }

    public void writeToCSV() {
        String csvFile = "player_stats.csv"; // Name of the CSV file
        try (FileWriter writer = new FileWriter(csvFile, true)) {
            // Writing player stats
            writer.append(String.format("%s,%d,(%d,%d),%d\n", this.uuid.toString(), this.moves, this.position[0], this.position[1], this.highScore.getScore()));

            System.out.println("Player stats appended to CSV file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }
}