package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Effect;

/** 
 * Represents the consequences of a {@code Card}.
 * Contains two {@code Effect} and a {@code String} with the answer.
 */
public class Decision {
    private final String answer;
    private final Effect effect1;
    private final Effect effect2;

    /**
     * Costructor of an element decision.
     * 
     * @param answer the {@code String} of the answer
     * @param effect1 the first {@code Effect}
     * @param effect2 the second {@code Effect}
     */
    public Decision(final String answer, final Effect effect1, final Effect effect2) {
        this.answer = answer;
        this.effect1 = effect1;
        this.effect2 = effect2;
    }

    /**
     * @return the {@code String} of the asnwer of this decision.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @return the first {@code Effect} of this decision.
     */
    public Effect getEffect1() {
        return effect1;
    }

    /**
     * @return the second {@code Effect} of this decision.
     */
    public Effect getEffect2() {
        return effect2;
    }

}
