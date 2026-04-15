package it.unibo.aurea.view.api;

import it.unibo.aurea.model.api.Card;

/**
 * Represents the main interface for the game's View.
 * It defines how the system shows outputs to the player.
 */
public interface GameView {

    /**
     * Updates the UI to show a new card to the player.
     *
     * @param card the {@code Card} to display
     */
    void displayCard(Card card);

    /**
     * Updates the UI bars/icons for a specific parameter.
     *
     * @param finances the current level of Finances
     * @param students the current level of Students
     * @param professors the current level of Professors
     * @param reputation the current level of Reputation
     */
    void updateParameters(int finances, int students, int professors, int reputation);

    /**
     * Displays the Game Over screen.
     *
     * @param message the reason of the game over
     */
    void showGameOver(String message);

    /**
     * this method handles the graphic of the victory situation.
     */
    void showVictory();

    /**
     * this method handles the graphic of the Game over situation.
     */
    void showDefeat();

    /**
     * Links the Controller to the View so the View can send user inputs (like button clicks).
     * 
     * @param controller the GameController.
     */
    void setController(it.unibo.aurea.controller.api.GameController controller);
}
