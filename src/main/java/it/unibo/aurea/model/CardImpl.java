package it.unibo.aurea.model;

import it.unibo.aurea.api.Card;
import it.unibo.aurea.api.Effect;

public class CardImpl implements Card{

    private String description;
    private Pair<String,String> answers;
    private Pair<Effect,Effect> weights;

    public CardImpl(String description, Pair<String, String> answers, Effect first, Effect second) {
        this.description = description;
        this.answers = answers;
        this.weights = new Pair<Effect,Effect>(first, second);
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public Pair<String, String> answers() {
        return this.answers;
    }

    @Override
    public Pair<Effect, Effect> weights() {
        return this.weights;
    }

}
