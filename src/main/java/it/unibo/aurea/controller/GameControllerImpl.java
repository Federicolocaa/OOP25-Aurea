package it.unibo.aurea.controller;

import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.aurea.controller.api.GameController;
import it.unibo.aurea.model.Decision;
import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.Effect;
import it.unibo.aurea.model.api.GameEngine;
import it.unibo.aurea.model.api.GameState;
import it.unibo.aurea.model.api.Parameter;
import it.unibo.aurea.model.api.ParameterType;
import it.unibo.aurea.view.api.GameView;
/**
 * Implementation of the GameController.
 * Manages the flow between the Deck (Model) and the GUI (View).
 */
public final class GameControllerImpl implements GameController {

    private final GameView view;
    private final GameEngine model;
    private final Map<ParameterType, Parameter> parametersMap;

    /**
     * Constructor for the controller.
     * Sets up the reactive connection between Model and View.
     *
     * @param view the {@code GameView} to update
     * @param model the {@code GameEngine} used
     */
    public GameControllerImpl(final GameView view, final GameEngine model) {
        this.view = view;
        this.model = model;
        
        //Create a Map to log in to the parameter in a costant time O(1)
        this.parametersMap = model.getParameters().stream()
        .collect(Collectors.toMap(Parameter::getName, p -> p));

        //Pattern OBSERVER -> Subscrive the view everytime the parameter change in the Model
        this.parametersMap.values().forEach(p -> p.addObserver((type, level) -> 
            this.view.updateParameters(
                parametersMap.get(ParameterType.FINANCES).getLevel(),
                parametersMap.get(ParameterType.STUDENTS).getLevel(),
                parametersMap.get(ParameterType.PROFESSORS).getLevel(),
                parametersMap.get(ParameterType.REPUTATION).getLevel()
            )
        ));
    }

    @Override
    public void startGame() {
        model.start();
        updateUI();
    }

    @Override
    public void makeDecision(final boolean isApproval) {
        final Card currentCard = model.getCurrentCard();
        
        // Ensure the game is running and a card is active
        if (currentCard != null && model.getGameState() == GameState.RUNNING) {
            
            // Retrieve the chosen decision (Approval or Refusal)
            final Decision decision = isApproval ? currentCard.getApproval() : currentCard.getRefusal();
            
            // Apply all effects to the corresponding parameters
            for (final Effect effect : decision.getEffects()) {
                final Parameter p = parametersMap.get(effect.getParameter());
                if (p != null) {
                    p.modify(effect.getDelta()); // automatically triggers the Observer notification
                }
            }

            // Mark the card as used and advance the game clock
            currentCard.changeUsage();
            model.getGameClock().nextTurn();
            
            // Update the UI to show the next card or the game over screen
            updateUI();
        }
    }

    /**
     * Synchronizes the View with the current state of the Model.
     */
    private void updateUI() {
        final GameState state = model.getGameState();
        
        if (state == GameState.RUNNING) {
            view.displayCard(model.getCurrentCard());
        } else {
            this.handleGameEnd(state);
        }
    }

    /**
     * Handles the graphic transitions for game termination.
     * * @param state the terminal state of the game
     */
    private void handleGameEnd(final GameState state) {
        switch (state) {
            case WON -> view.showVictory();
            case LOST -> view.showDefeat();
            default -> throw new IllegalStateException("Unexpected State: " + state);
        }
    }

    @Override
    public void quitGame() {
        // TODO I've should understand if this is usable to quit safely with the 
        // GUI or to save localy the data, or for now is useless.
    }
}
