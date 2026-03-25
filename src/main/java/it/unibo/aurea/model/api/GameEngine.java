package it.unibo.aurea.model.api;

/**
 * is the class, who manage the game, when it starts, how it ends.
 */
public interface GameEngine {

    /**
     * start the game with the selected configuration, probably the configuration will be edited by the programers.
     * 
     * @param config contains infos, like number of semesters, turns...
     */
    void start(GameConfig config);

    /**
     * verifys if the parameters are ok.
     * 
     * @return true, if is game over
     */
    boolean isGameOver();

    /**
     * Checks whether the game has reached a terminal condition.
     *
     * @return true if the game is over and the endgame phase should begin, false otherwise.
     */
    boolean isGameFinished();
}
