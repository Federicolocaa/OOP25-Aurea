package it.unibo.aurea.model;

import java.util.List;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.Effect;

public class CardImpl implements Card {
    private CharacterType character;
    private String description;
    private Decision refusal;
    private Decision approval;
    private boolean used;

    public CardImpl(CharacterType character, String description, Decision refusal, Decision approval) {
        this.character = character;
        this.description = description;
        this.refusal = refusal;
        this.approval = approval;
        this.used = false;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Pair<String, String> getAnswers() {
        return new Pair<String,String>(refusal.getAnswer(), approval.getAnswer());
    }

    @Override
    public boolean isUsed() {
        return this.used;
    }

    @Override
    public CharacterType getCharacter() {
        return this.character;
    }

    @Override
    public Decision getRefusal() {
        return this.refusal;
    }

    @Override
    public Decision getApproval() {
        return this.approval;
    }

    @Override
    public List<Effect> getAllEffects() {
        return List.of(refusal.getEffect1(), refusal.getEffect2(), approval.getEffect1(), approval.getEffect2());  
    }

}
