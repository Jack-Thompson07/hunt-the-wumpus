/* 
import java.util.Random;

public class Chest {
    public enum Content { GOLD, ARROWS }
    private Content content;
    private int quantity;
    private int[] position;

    public Chest(Content content, int quantity, int[] position) {
        this.content = content;
        this.quantity = quantity;
        this.position = position;
    }

    public Content getContent() {
        return content;
    }

    public int getQuantity() {
        return quantity;
    }

    public int[] getPosition() {
        return position;
    }

    public void openChest(Player player) {
        if (content == Content.GOLD) {
            player.addGold(quantity);
        } else if (content == Content.ARROWS) {
            player.addArrows(quantity);
        }
        // Remove the chest from the map after opening
        // This assumes there's a method in the map to remove a chest
    }
}

*/