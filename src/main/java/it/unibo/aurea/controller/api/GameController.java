package it.unibo.aurea.controller.api;

import java.util.Set;

import it.unibo.aurea.model.api.ParameterType;

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
     * Previews the parameters that will be affected by a decision, without altering their actual values.
     * Useful for updating the UI hints (e.g., hovering or dragging a card).
     *
     * @param isApproval true to preview the approval decision, false for the refusal.
     * @return a Set of ParameterType that will be modified by the decision.
     */
    Set<ParameterType> previewDecision(boolean isApproval);

    /**
     * Quits the application.
     */
    void quitGame();
}
