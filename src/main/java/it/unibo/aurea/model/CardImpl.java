package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.Effect;

public class CardImpl implements Card {

    private String description;
    private Pair<String,String> answers;
    private CharacterType character;
    private Pair<Effect,Effect> effects;
    private boolean used;

    public CardImpl(CharacterType character, String description, Decision reject, Decision approve) {
        this.description = description;
        this.character = character;
        this.answers = new Pair<String,String>(reject.getAnswer(), approve.getAnswer());
        this.effects = new Pair<Effect,Effect>(reject.getEffect1(), approve.getEffect2());
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
        return this.effects;
    }

    @Override
    public boolean isUsed() {
        return this.used;
    }

    @Override
    public CharacterType getCharacter() {
        return this.character;
    }

}
