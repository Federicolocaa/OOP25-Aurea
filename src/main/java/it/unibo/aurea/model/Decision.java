package it.unibo.aurea.model;

import java.util.List;

import it.unibo.aurea.model.api.Effect;

/**
 * Represents the consequences of a {@code Card}.
 * Contains a dynamic list of {@code Effect} and a {@code String} with the answer.
 */
public class Decision {
    private final String answer;
    private final List<Effect> effects;

    /**
     * Constructor of an element decision.
     * Uses varargs to allow any number of effects (0, 1, 2, or more).
     *
     * @param answer the {@code String} of the answer
     * @param effects a variable number of {@code Effect}
     */
    public Decision(final String answer, final Effect... effects) {
        this.answer = answer;
        this.effects = List.of(effects);
    }

    /**
     * @return the {@code String} of the answer of this decision.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @return a {@code List} of all the effects of this decision.
     */
    public List<Effect> getEffects() {
        return effects;
    }

}
