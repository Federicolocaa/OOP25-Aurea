package it.unibo.aurea.api;

import it.unibo.aurea.model.Pair;

/**
 * Represents a question made to the player.
 * 
 */
public interface Card {
    /**
     * Get the text of a question that desribes its purpose.
     * 
     * @return the text of a question
     */
    String description();

    /**
     * Get the possible answers of the following question.
     * 
     * @return the text of possible the answers
     */
    Pair<String,String> answers();

    /**
     * Get the effect of the card on the parameters.
     * 
     * @return two element {@code Effect} that modify two parameters.
     */
    Pair<Effect,Effect> weights();
}
