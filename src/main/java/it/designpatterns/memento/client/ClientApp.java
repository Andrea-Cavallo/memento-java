package it.designpatterns.memento.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.designpatterns.memento.caretaker.GameLevelHistory;
import it.designpatterns.memento.originator.GameLevel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class ClientApp {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The main method of the ClientApp class.
     * It demonstrates the usage of the GameLevel and GameLevelHistory classes by creating a game level,
     * modifying its state, saving and restoring its states, and logging the game state.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel( 0, 0, new ArrayList<>( Arrays.asList( "Sword", "Shield" ) ), 0 );
        GameLevelHistory history = new GameLevelHistory();

        // Save the initial state
        history.save( gameLevel );
        logState( "Initial game state:", gameLevel );

        // Modify the state
        gameLevel.setPlayerPositionX( 5 );
        gameLevel.setPlayerPositionY( 10 );
        gameLevel.getInventory().add( "Bow" );
        gameLevel.setScore( 50 );


        // Save the modified state
        history.save( gameLevel );
        logState( "Modified game state:", gameLevel );

        // Further modifications
        gameLevel.setPlayerPositionX( 15 );
        gameLevel.setPlayerPositionY( 20 );
        gameLevel.getInventory().add( "Healing Potion" );
        gameLevel.setScore( 100 );
        logState( "Further modified game state:", gameLevel );

        // First undo: should return to the state after the first modification
        history.undo( gameLevel );
        logState( "Game state after the first undo:", gameLevel );

        // Second undo: should return to the initial state
        history.undo( gameLevel );
        logState( "Game state after the second undo (initial state):", gameLevel );
    }

    /**
     * Logs the state of the game level along with a message.
     *
     * @param message   the message to be logged along with the game state
     * @param gameLevel the game level object representing the state of the game
     */
    private static void logState(String message, GameLevel gameLevel) {
        try {
            String gameStateJson = objectMapper.writeValueAsString( gameLevel );
            log.info( "{} {}", message, gameStateJson );
        } catch (JsonProcessingException e) {
            log.error( "Error serializing game state", e );
        }
    }
}


