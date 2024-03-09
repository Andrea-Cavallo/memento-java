package it.designpatterns.memento.originator;

import it.designpatterns.memento.utils.DeepCopyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class GameLevel {
    private int playerPositionX;
    private int playerPositionY;
    private List<String> inventory;
    private int score;

    /**
     * Saves the current state of the game level in a Memento object.
     *
     * @return a Memento object containing a copy of the player's position, inventory, and score
     */
    public Memento saveMemento() {
        List<String> inventoryCopy = new ArrayList<>( this.inventory );
        return new Memento( playerPositionX, playerPositionY, inventoryCopy, score );
    }

    /**
     * Currently, this method is not being used  it could be useful to go through a utility class.
     * <p>
     * Saves the current state of the game level in a new Memento object by performing a deep copy of the game level object.
     * The Memento object contains a copy of the player's position, inventory, and score.
     *
     * @return a Memento object containing a copy of the player's position, inventory, and score
     * @see GameLevel.Memento
     */
    public Memento saveWithDeepCopyOnMemento() {
        // Assuming GameLevel is properly serializable with Jackson
        GameLevel deepCopiedState = DeepCopyUtil.deepCopy( this, GameLevel.class );
        return new Memento( deepCopiedState.playerPositionX, deepCopiedState.playerPositionY, deepCopiedState.inventory, deepCopiedState.score );
    }


    /**
     * Restores the game level state using the provided Memento object.
     * The player's position, inventory, and score are updated based on the values stored in the Memento.
     *
     * @param memento the Memento object containing the saved state of the game level
     */
    public void restoreOnMemento(Memento memento) {
        if (memento == null) {
            throw new IllegalArgumentException( "Cannot restore from a null state." );
        }
        this.playerPositionX = memento.getPlayerPositionX();
        this.playerPositionY = memento.getPlayerPositionY();
        this.inventory = new ArrayList<>( memento.getInventory() );
        this.score = memento.getScore();
    }


    /**
     * The Memento class represents a snapshot of the state of a GameLevel object.
     * It stores the player's position, inventory, and score.
     */
    @Data
    @AllArgsConstructor
    public static class Memento {
        private final int playerPositionX;
        private final int playerPositionY;
        private final List<String> inventory;
        private final int score;
    }
}

