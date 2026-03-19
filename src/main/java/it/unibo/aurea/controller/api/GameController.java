package it.unibo.aurea.controller.api;

/**
 * Represents the Controller in the MVC architecture.
 * It acts as a bridge between the View and the Model.
 */
public interface GameController {

    /**
     * Starts the game, initializing the deck and showing the first card.
     */
    void startGame();

    /**
     * Called when the player makes a decision on the current card.
     *
     * @param isApproval {@code true} if the player accepted the proposal, {@code false} if refused
     */
    void makeDecision(boolean isApproval);

    /**
     * Quits the application.
     */
    void quitGame();
}
