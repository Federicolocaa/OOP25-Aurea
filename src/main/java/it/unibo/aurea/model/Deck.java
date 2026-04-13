package it.unibo.aurea.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.ParameterType;
import it.unibo.aurea.model.dto.CardDTO;
import it.unibo.aurea.model.dto.CardsFile;
import it.unibo.aurea.model.dto.EffectDTO;
import it.unibo.aurea.model.dto.FollowUpDTO;
import it.unibo.aurea.model.dto.FollowUpsFile;

/**
 * It represents the container of all the cards of the game. 
 */
public class Deck {
    private final List<Card> cardsDeck = new ArrayList<>();
    private final List<FollowUpImpl> followUps = new ArrayList<>();

    /**
     * Constructor of the deck from a .yaml file. 
     * 
     * @throws IOException if the reading from the file doesn't work
     */
    public Deck() {
        try (
            InputStream cardIn = Deck.class.getClassLoader().getResourceAsStream("cards.yaml");
            InputStream followUpIn = Deck.class.getClassLoader().getResourceAsStream("followups.yaml")) {
            if (Objects.isNull(cardIn) || Objects.isNull(followUpIn)) {
                throw new IllegalStateException("unable to find .yaml file");
            }
            final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            final CardsFile cFile = mapper.readValue(cardIn, CardsFile.class);
            final FollowUpsFile fuFile = mapper.readValue(followUpIn, FollowUpsFile.class);
            this.cardsDeck.addAll(cFile.cards().stream().map(this::toCard).toList());
            this.followUps.addAll(fuFile.followups().stream().map(this::toFollowUp).toList());
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load cards and follow-up from YAML", e);
        }
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

    /**
     * Private method that converts an istance of {@code CardDTO} in a {@code Card}.
     * 
     * @param cardDto the element to convert
     * @return a {@code Card} with the same characteristics of the input
     */
    private Card toCard(final CardDTO cardDto) {
        final var res = new CardImpl.Builder()
            .id(cardDto.id())
            .character(cardDto.character())
            .description(cardDto.description())
            .textRefusal(cardDto.refusal().text())
            .textApproval(cardDto.approval().text());

            for (final EffectDTO e : cardDto.refusal().effects()) {
                res.effectRefusal(e.parameter(), e.delta());
            }

            for (final EffectDTO e : cardDto.approval().effects()) {
                res.effectApproval(e.parameter(), e.delta());
            }

        return res.build();
    }

    /**
     * Private method that converts an istance of {@code FollowUpDTO} in a {@code FollowUpImpl}.
     * 
     * @param fuDTO the input element
     * @return the correct output
     */
    private FollowUpImpl toFollowUp(final FollowUpDTO fuDTO) {
        return new FollowUpImpl(fuDTO.parentId(), fuDTO.childId(), fuDTO.trigger(), fuDTO.delayTurn());
    }

    /**
     * @return a copy list of all the follow-up
     */
    public List<FollowUpImpl> getAllFollowUps() {
        return List.copyOf(this.followUps);
    }
}
