package it.unibo.aurea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.ParameterType;
import it.unibo.aurea.model.dto.CardDTO;
import it.unibo.aurea.model.dto.CardsFile;

/** 
 * Small test to ensure loading cards from .yaml file works.
 */
class CardLoaderTest {
    @Test
    void shouldLoadCardsFromYaml() throws IOException {
        final InputStream input = getClass().getClassLoader().getResourceAsStream("cards.yaml");
        assertNotNull(input, "cards.yaml should exist");

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        final CardsFile file = mapper.readValue(input, CardsFile.class);

        assertNotNull(file);
        assertNotNull(file.cards());
        assertFalse(file.cards().isEmpty());

        final CardDTO first = file.cards().get(0);
        assertEquals(CharacterType.PROFESSOR, first.character());
        assertEquals(first.refusal().effects().getFirst().parameter(), ParameterType.PROFESSORS);
        assertEquals(first.refusal().effects().getLast().parameter(), ParameterType.STUDENTS);
        assertNotNull(first.approval());
    }

}
