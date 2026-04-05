package it.unibo.aurea.controller;

import it.unibo.aurea.controller.api.GameController;
//import it.unibo.aurea.model.Deck;
import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameEngine;
import it.unibo.aurea.view.api.GameView;

/**
 * Implementation of the GameController.
 * Manages the flow between the Deck (Model) and the GUI (View).
 */
public final class GameControllerImpl implements GameController {

    private final GameView view;
    private final GameEngine model;
    private Card currentCard;

    /**
     * Constructor for the controller.
     *
     * @param view the {@code GameView} to update
     * @param model the {@code GameEngine} used.
     */
    public GameControllerImpl(final GameView view, final GameEngine model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void startGame() {
        model.start();
        // Extracts the first card (for now, the first of the list)
        currentCard = model.getCurrentCard(); 
        view.displayCard(this.currentCard);
    }

    @Override
    public void makeDecision(final boolean isApproval) {
        // Minimal logic to satisfy Checkstyle/PMD/SpotBugs.
        if (this.currentCard != null && isApproval) {
            this.currentCard.changeUsage();
        }
    }

    @Override
    public void quitGame() {
        // TODO
    }

}
