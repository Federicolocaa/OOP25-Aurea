package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Effect;

/** 
 * Represents the consequences of a {@code Card}.
 * Contains two {@code Effect} and a {@code String} with the answer.
 *  
 */
public class Decision {
    private String answer;
    private Effect effect1;
    private Effect effect2;

    public Decision(String answer, Effect effect1, Effect effect2) {
        this.answer = answer;
        this.effect1 = effect1;
        this.effect2 = effect2;
    }

    public String getAnswer() {
        return answer;
    }

    public Effect getEffect1() {
        return effect1;
    }

    public Effect getEffect2() {
        return effect2;
    }
    
   
}
