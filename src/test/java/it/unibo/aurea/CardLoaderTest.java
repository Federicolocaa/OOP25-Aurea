package it.unibo.aurea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import it.unibo.aurea.model.Deck;
import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.ParameterType;
import it.unibo.aurea.model.dto.CardDTO;
import it.unibo.aurea.model.dto.CardsFile;

/** 
 * Small test to ensure loading cards from .yaml file works.
 */
class CardLoaderTest {
    private static final int MEDIUM_CHANGE = 7;
    private static final int ACTUAL_SIZE = 4;

    @Test
    void shouldLoadCardsFromYaml() throws IOException {
        final InputStream input = getClass().getClassLoader().getResourceAsStream("cards.yaml");
        assertNotNull(input, "cards.yaml should exist");

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        final CardsFile file = mapper.readValue(input, CardsFile.class);

        assertNotNull(file);
        assertNotNull(file.cards());
        assertEquals(ACTUAL_SIZE, file.cards().size());

        final CardDTO first = file.cards().get(0);
        assertEquals(CharacterType.PROFESSOR, first.character());
        assertEquals(ParameterType.PROFESSORS, first.refusal().effects().getFirst().parameter());
        assertEquals("No, the budget is too tight", first.refusal().text());
        assertNotNull(first.approval());
        final CardDTO third = file.cards().get(2);
        assertEquals(CharacterType.MUM, third.character());
        assertEquals(ParameterType.REPUTATION, third.approval().effects().getFirst().parameter());
        assertEquals(MEDIUM_CHANGE, third.approval().effects().getFirst().delta()); 
    }

    @Test
    void deckLoader() throws IOException {
        final Deck cardsDeck = new Deck();
        final Card first = cardsDeck.getAllCards().get(0);
        assertEquals(CharacterType.PROFESSOR, first.getCharacter());
        assertEquals(ParameterType.PROFESSORS, first.getRefusal().getEffects().getFirst().getParameter());
        assertEquals("No, the budget is too tight", first.getRefusal().getAnswer());
        final Card third = cardsDeck.getAllCards().get(2);
        assertEquals(CharacterType.MUM, third.getCharacter());
        assertEquals(ParameterType.REPUTATION, third.getApproval().getEffects().getFirst().getParameter());
        assertEquals(MEDIUM_CHANGE, third.getApproval().getEffects().getFirst().getDelta());
    }

    @Test
    void imageLoader() throws IOException {
        final Deck cards = new Deck();
        for (final Card c : cards.getAllCards()) {
            assertNotNull(
                getClass().getResource(c.getCharacter().getImagePath()), 
                "image not found" + c.getCharacter().getImagePath());
        }
    }
}
