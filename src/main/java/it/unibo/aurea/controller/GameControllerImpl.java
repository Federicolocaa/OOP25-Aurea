package it.unibo.aurea.controller;

import it.unibo.aurea.controller.api.GameController;
//import it.unibo.aurea.model.Deck;
//import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.GameEngine;
import it.unibo.aurea.model.api.GameState;
import it.unibo.aurea.view.api.GameView;

/**
 * Implementation of the GameController.
 * Manages the flow between the Deck (Model) and the GUI (View).
 */
public final class GameControllerImpl implements GameController {

    private final GameView view;
    private final GameEngine model;
    //private Card currentCard;

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

        while (model.getGameState() == GameState.RUNNING) {
            /* 
            THIS CONTENT IS APPROXIMATE
            view.showCard(model.getCurrentCard());

            Choice choice = view.getUserChoice();

            model.applyChoice(choice);

            */
            model.getGameClock().nextTurn();
        }

        handleGameEnd();
    }

    /**
     * activates all the procedures for the end of the game, it's private because it can be called only affter the quit.
     */
    private void handleGameEnd() {
        final GameState state = model.getGameState();
        switch (state) {
            case WON -> view.showVictory();
            case LOST -> view.showDefeat();
            case RUNNING -> throw new IllegalStateException("Unexpected state");
        }
    }

    @Override
    public void makeDecision(final boolean isApproval) {
        // Minimal logic to satisfy Checkstyle/PMD/SpotBugs.
        throw new IllegalStateException("not implemented");
    }

    @Override
    public void quitGame() {
        // TODO I've should understand if this is usable to quit safely with the 
        // GUI or to save localy the data, or for now is useless.
    }
}
