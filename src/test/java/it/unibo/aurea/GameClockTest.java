package it.unibo.aurea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.aurea.model.GameClockImpl;
import it.unibo.aurea.model.GameConfigImpl;
import it.unibo.aurea.model.api.GameClock;
import it.unibo.aurea.model.api.GameConfig;

/**
 * this class is used to test the functionality of the gameClock
 * this is uìsed to chek that every modification to the implementation doesn't 
 * contains errors in the functionality.
 */
final class GameClockTest {
    private GameClock clock;
    //private GameConfig config;

    @BeforeEach
    void configuration() {
        final GameConfig config = GameConfigImpl.createStandard(); //TO DO check if is better
        //to have it like a field or a a local variable
        clock = new GameClockImpl(config);
    }

    @Test
    void testSingleTurnProgression() {
        final int initialTurn = clock.getCurrentTurn();
        final int initialSemester = clock.getCurrentSemester();

        clock.nextTurn();

        // Adapt the test to support different game configurations.
        // Currently, it assumes that after a single decision the semester does not change,
        // which is specific to the standard game mode.
        // In the future, a game mode could be introduced where a single card affects an entire parameter cycle.
        assertEquals(initialTurn + 1, clock.getCurrentTurn(), "The turn should advance by exactly 1");
        assertEquals(initialSemester, clock.getCurrentSemester(), "The semester cannot change after just one turn");
    }

}
