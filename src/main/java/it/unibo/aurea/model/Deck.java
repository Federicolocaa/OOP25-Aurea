package it.unibo.aurea.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.ParameterType;

/**
 * It represents the container of all the cards of the game. 
 */
public class Deck {
    public static final int LARGE_CHANGE = 15;
    public static final int MEDIUM_CHANGE = 7;
    public static final int SMALL_CHANGE = 3;

    private final List<Card> cardsDeck = new ArrayList<>();

    /**
     * Constructor of the deck. It makes all the cards ready for the game. 
     */
    public Deck() {
        this.makeStudentCards();
        this.makeProfessorCards();
        this.makeMumCards();
        this.makeBusinessmanCards();
    }

    private void makeProfessorCards() {
        this.cardsDeck.add(new CardImpl.Builder()
        .description("Rector, the faculty is requesting funding for a new research "
            + "initiative. Will the university support it?")
        .textRefusal("No, the budget is too tight")
        .effectRefusal(ParameterType.PROFESSORS, -MEDIUM_CHANGE)
        .effectRefusal(ParameterType.STUDENTS, -SMALL_CHANGE)
        .textApproval("Yes, research must be supported")
        .effectApproval(ParameterType.PROFESSORS, MEDIUM_CHANGE)
        .effectApproval(ParameterType.FINANCES, -MEDIUM_CHANGE)
        .build());

    }

    private void makeMumCards() {
    }

    private void makeBusinessmanCards() {
    }

    private void makeStudentCards() {
    }

    /**
     * It gets all the card in the deck. 
     * 
     * @return a {@code List} of cards
     */
    public List<Card> getAllCards() {
        return new ArrayList<>(this.cardsDeck);
    }

    /**
     * It filters an input list returning the cards associated with a character.
     * 
     * @param input the list to filter
     * @param character the term of comparison
     * @return a {@code List} of cards matched with the input {@code CharacterType}
     */
    public List<Card> getByCharacter(final List<Card> input, final CharacterType character) {
        return input.stream()
            .filter(c -> c.getCharacter() == character)
            .collect(Collectors.toList());
    }

    /**
     * It filters an input list returning the cards that modify a parameter.
     * 
     * @param input the list to filter
     * @param parameter the term of comparison
     * @return a {@code List} of cards with an effect on the input {@code ParameterType}
     */
    public List<Card> getByParameter(final List<Card> input, final ParameterType parameter) {
        return input.stream()
            .filter(c -> c.getAllEffects().stream()
            .anyMatch(e -> e.getParameter() == parameter))
            .collect(Collectors.toList());
    }

    /**
     * It filters an input list returning the cards containing a specific delta.
     * 
     * @param input the list to filter
     * @param delta the term of comparison
     * @return a {@code List} of cards with an {@code int} of how a parameter change
     */
    public List<Card> getByDelta(final List<Card> input, final int delta) {
        return input.stream()
            .filter(c -> c.getAllEffects().stream()
            .anyMatch(e -> e.getDelta() == delta))
            .collect(Collectors.toList());
    }
}
