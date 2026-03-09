package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.Effect;

public class CardImpl implements Card {

    private String description;
    private Pair<String,String> answers;
    private CharacterType character;
    private Pair<Effect,Effect> weights;
    private boolean used;

    public CardImpl(CharacterType character, String description, Pair<String, String> answers, Effect first, Effect second) {
        this.description = description;
        this.character = character;
        this.answers = answers;
        this.weights = new Pair<Effect,Effect>(first, second);
        this.used = false;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Pair<String, String> getAnswers() {
        return this.answers;
    }

    @Override
    public Pair<Effect, Effect> getEffects() {
        return this.weights;
    }

    @Override
    public boolean isUsed() {
        return this.used;
    }

    @Override
    public CharacterType getCharacter() {
        return character;
    }

}
