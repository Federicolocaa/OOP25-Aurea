package it.unibo.aurea.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.ParameterType;

/** 
 * It represents the container of all the cards of the game.
 *  
 */
public class Deck {
    private List<Card> deck = new ArrayList<>();

    public Deck() {
        this.makeStudentCards();
        this.makeProfessorCards();
        this.makeMumCards();
        this.makeBusinessmanCards();
    }

    private void makeProfessorCards() {
        this.deck.add(new CardImpl(CharacterType.PROFESSOR,
        "Rector, the faculty is requesting funding for a new research initiative. Will the university support it?",
        new Decision("No, the budget is too tight", 
                new EffectImpl(ParameterType.PROFESSORS, -7), 
                new EffectImpl(ParameterType.STUDENTS, -3)),
        new Decision("Yes, research must be supported", 
                new EffectImpl(ParameterType.PROFESSORS, 7), 
                new EffectImpl(ParameterType.FINANCES, -7))));
    }

    private void makeMumCards() {
        this.deck.add(new CardImpl(CharacterType.MUM,
        "Rector, many families care about the university's public image. Would you invest in a campaign to improve the institution's reputation?",
        new Decision("No, that would be unnecessary spending", 
                new EffectImpl(ParameterType.REPUTATION, -7), 
                new EffectImpl(ParameterType.STUDENTS, -3)),
        new Decision("Yes, let's promote the university", 
                new EffectImpl(ParameterType.REPUTATION, 15), 
                new EffectImpl(ParameterType.FINANCES, -7))));
    }

    private void makeBusinessmanCards() {
        this.deck.add(new CardImpl(CharacterType.BUSINESSMAN,
        "Rector, my company would like to sponsor a new technology lab for the university. Would you accept our investment?",
        new Decision("No, we prefer to remain independent", 
                new EffectImpl(ParameterType.FINANCES, -7), 
                new EffectImpl(ParameterType.REPUTATION, -3)),
        new Decision("Yes, we welcome the investment", 
                new EffectImpl(ParameterType.FINANCES, 15), 
                new EffectImpl(ParameterType.REPUTATION, 3))));
    }

    private void makeStudentCards() {
        this.deck.add(new CardImpl(CharacterType.STUDENT,
        "Rector, would you consider funding a large student festival on campus to improve student life and strengthen our community?",
        new Decision("No, we can't", 
            new EffectImpl(ParameterType.STUDENTS, -7), 
            new EffectImpl(ParameterType.REPUTATION, -3)),
        new Decision("Yeah, do it", 
            new EffectImpl(ParameterType.STUDENTS, 15), 
            new EffectImpl(ParameterType.FINANCES, -3))));

    }

    /**
     * It gets all the card in the deck. 
     * 
     * @return a {@code List} of cards
     */
    public List<Card> getAllCards() {
        return new ArrayList<>(this.deck);
    }

    /**
     * It filters an input list returning the cards associated with a character.
     * 
     * @param input the list to filter
     * @param character the term of comparison
     * @return a {@code List} of cards matched with the input {@code CharacterType}
     */
    public List<Card> getByCharacter(List<Card> input, CharacterType character) {
        return input.stream()
            .filter(c -> c.getCharacter().equals(character))
            .collect(Collectors.toList());
    }

    /**
     * It filters an input list returning the cards that modify a parameter.
     * 
     * @param input the list to filter
     * @param parameter the term of comparison
     * @return a {@code List} of cards with an effect on the input {@code ParameterType}
     */
    public List<Card> getByParameter(List<Card> input, ParameterType parameter) {
        return input.stream()
            .filter(c -> c.getAllEffects().stream()
            .anyMatch(e -> e.getParameter().equals(parameter)))
            .collect(Collectors.toList());
    }

    /**
     * It filters an input list returning the cards containing a specific delta.
     * 
     * @param input the list to filter
     * @param delta the term of comparison
     * @return a {@code List} of cards with an {@code int} of how a parameter change
     */
    public List<Card> getByDelta(List<Card> input, int delta) {
        return input.stream()
            .filter(c -> c.getAllEffects().stream()
            .anyMatch(e -> e.getDelta() == delta))
            .collect(Collectors.toList());
    }
}
