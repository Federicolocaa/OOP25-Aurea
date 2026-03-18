package it.unibo.aurea.model.api;

/** 
 * Manage the time durng the game.
 * 
 */
public interface GameClock {
    /**
     * 
     * @return how many cards were already presented to the player
     * the number start from 0. 
     * 
     */
    int getCardIndex();

    /**
     * 
     * @return how many cards are presented for semester to the player
     */
    int getCardsPerSemester();

    /**
     * 
     * increase the number of card already shown to the player
     */
    void increaseCardIndex();
}
