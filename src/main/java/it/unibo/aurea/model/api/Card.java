package it.unibo.aurea.model.api;

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
    String getDescription();

    /**
     * Get the possible answers of the following question.
     * 
     * @return the text of possible the answers
     */
    Pair<String,String> getAnswers();

    /**
     * Get the effect of the card on the parameters.
     * 
     * @return two element {@code Effect} that modify two parameters.
     */
    Pair<Effect,Effect> getEffects();

    /** 
     * Indicates if a card has been used or not.
     * 
     * @return true if it's been used. Default is false
     */
    boolean isUsed();

    /** 
     * Idicates the character assigned to this card based on the higher parameter modified.
     * 
     * @return the {@code Characters} of this card
     */
    CharacterType getCharacter();
}
