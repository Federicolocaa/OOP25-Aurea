package it.unibo.aurea.model.api;

/**
 * Element of the game that decides what card give to the player based on his rule.
 */
public interface Character {

    /**
     * Algorithm that decides what card give to the player based on the history and the state of the parameters.
     * 
     * @param finance the level of the finance parameter
     * @param student the level of the student parameter
     * @param professor the level of the professor parameter
     * @param reputation the level of the reutation parameter
     * @return the next card of the game
      */
    Card computeNextCard(Integer finance, Integer student, Integer professor, Integer reputation);
}
