package it.unibo.aurea.model.api;

import java.util.List;

import it.unibo.aurea.model.ParameterImpl;

/**
 * is the class, who manage the game, when it starts, how it ends.
 */
public interface GameEngine {

    /**
     * start the game with the selected configuration, probably the configuration will be edited by the programers.
     */
    void start();

    /**
     * verifys if the parameters are ok.
     * 
     * @return true, if is game over it does every single check for any condition.
     */
    boolean isGameOver();

    /**
     * Checks the game has reached a terminal condition.
     *
     * @return true if the game is over and the endgame phase should begin, false otherwise.
     */
    boolean isGameFinished();

    /**
     * @return the actual configuration for this game.
     */
    GameConfig getGameConfig();

    /**
     * @return the current card.
     */
    Card getCurrentCard();

    /**
     * @return the list of parameters, it gives direct access to the object, so use it only if you need to change something.
     */
    List<Parameter> getParameters();

    /**
     * @return  copy of the list of parameters.
     */
    List<Parameter> getCopyOfParameters();

    GameClock getGameClock();
}
