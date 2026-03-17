package it.unibo.aurea.model.api;

import java.util.List;

import it.unibo.aurea.model.Decision;
import it.unibo.aurea.model.Pair;

/**
 * Represents a question made to the player. 
 * The {@code Decision} created must be in this order: first the refusal, second the approval.
 * 
 */
public interface Card {
    /**
     * Get the text of a question that desribes its purpose.
     * 
     * @return the {@code String} of a question
     */
    String getDescription();

    /**
     * Get the possible answers of the following question.
     * 
     * @return a {@code Pair<>} containing the text of the answers 
     */
    Pair<String,String> getAnswers();

    /** 
     * Indicates if a card has been used or not.
     * 
     * @return true if it's been used. Di default is false
     */
    boolean isUsed();

    /** 
     * Idicates the character assigned to this card.
     * 
     * @return the {@code Characters} of this card
     */
    CharacterType getCharacter();

    /**
     *  Idicates the refusal of this card.
     * 
     * @return a {@code Decision} with the consequences of the refusal
     */
    Decision getRefusal();

    /**
     *  Idicates the approval of this card.
     * 
     * @return a {@code Decision} with the consequences of the approval
     */
    Decision getApproval();

    /**
     * Represent an overview on the consequences of this card on the parameters.
     * 
     * @return a {@code List} of the effect connected with this card
     */
    List<Effect> getAllEffects();
}
