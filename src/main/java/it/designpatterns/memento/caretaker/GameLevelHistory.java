package it.designpatterns.memento.caretaker;


import it.designpatterns.memento.originator.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevelHistory class is responsible for managing the history of game level states.
 * It allows the game level to be saved and restored to previous states.
 */
public class GameLevelHistory {
    private final List<GameLevel.Memento> history = new ArrayList<>();

    public void save(GameLevel gameLevel) {
        history.add( gameLevel.saveMemento() );
    }

    public void undo(GameLevel gameLevel) {
        if (!history.isEmpty()) {
            gameLevel.restoreOnMemento( history.remove( history.size() - 1 ) );
        } else {
            System.out.println( "Cannot undo further." );
        }
    }
}
