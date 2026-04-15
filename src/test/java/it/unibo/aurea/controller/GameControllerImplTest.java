package it.unibo.aurea.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.aurea.controller.api.GameController;
import it.unibo.aurea.model.Deck;
import it.unibo.aurea.model.GameConfigImpl;
import it.unibo.aurea.model.GameEngineImpl;
import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.view.api.GameView;

/**
 * Integration and robustness tests for the GameControllerImpl.
 * Uses a FakeView to test the MVC communication without launching JavaFX.
 */
class GameControllerImplTest {

    private GameController controller;
    private FakeView fakeView;

    /**
     * Sets up a fresh MVC environment before each test.
     */
    @BeforeEach
    public void setUp() {
        try {
            // Initialize the real Model (just like in the Main class)
            final Deck deck = new Deck();
            final GameEngineImpl engine = new GameEngineImpl(GameConfigImpl.createStandard(), deck);

            // Initialize the fake View
            fakeView = new FakeView();

            // Create the Controller to be tested
            controller = new GameControllerImpl(fakeView, engine);

        } catch (final IllegalStateException e) {
            throw new IllegalStateException("Error during test initialization", e);
        }
    }

    @Test
    void testStartGameTriggersView() {
        // When I start the game...
        controller.startGame();

        // ...the fake view must have received the order to display a card
        assertTrue(fakeView.isCardDisplayed(), "The View should have displayed the first card.");
    }

    @Test
    void testMakeDecisionUpdatesView() {
        controller.startGame();
        fakeView.setCardDisplayed(false); // Reset the spy flag

        // When the player makes a decision...
        controller.makeDecision(true);

        // ...the controller must move to the next card and update the view
        assertTrue(fakeView.isCardDisplayed(), "The View should have been updated after the decision.");
    }

    @Test
    void testObserverUpdatesParameters() {
        controller.startGame();
        fakeView.setParametersUpdated(false); // Reset the spy flag

        // When a decision is made, parameters are modified
        controller.makeDecision(false);

        // The Observer pattern should automatically notify the view
        assertTrue(fakeView.isParametersUpdated(), "The View should have been notified of parameter changes via Observer.");
    }

    @Test
    void testRobustnessNoCrash() {
        controller.startGame();

        // Verify that the controller never crashes (thanks to our fail-safe checks)
        // even when simulating rapid, consecutive calls
        assertDoesNotThrow(() -> {
            controller.makeDecision(true);
            controller.makeDecision(false);
            controller.makeDecision(true);
        }, "The controller should never throw unexpected exceptions during decisions.");
    }

    /**
     * A fake class that acts as a "spy" on the Controller's actions without using the real GUI.
     */
    private static final class FakeView implements GameView {

        // "Spies" to track if methods have been called
        private boolean isCardDisplayed;
        private boolean isParametersUpdated;

        public boolean isCardDisplayed() {
            return this.isCardDisplayed;
        }

        public void setCardDisplayed(final boolean status) {
            this.isCardDisplayed = status;
        }

        public boolean isParametersUpdated() {
            return this.isParametersUpdated;
        }

        public void setParametersUpdated(final boolean status) {
            this.isParametersUpdated = status;
        }

        @Override
        public void displayCard(final Card card) {
            this.isCardDisplayed = true;
        }

        @Override
        public void updateParameters(final int finances, final int students, final int professors, final int reputation) {
            this.isParametersUpdated = true;
        }

        @Override
        public void showVictory() {
            // Empty stub for testing
        }

        @Override
        public void showDefeat() {
            // Empty stub for testing
        }

        @Override
        public void showGameOver(final String reason) {
            // Empty stub for testing
        }
    }
}
