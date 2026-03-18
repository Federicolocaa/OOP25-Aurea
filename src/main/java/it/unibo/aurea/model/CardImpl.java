package it.unibo.aurea.model;

import java.util.List;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.Effect;

/**
 * {@inheritDoc}.
 */
public class CardImpl implements Card {
    private final CharacterType character;
    private final String description;
    private final Decision refusal;
    private final Decision approval;
    private boolean usage;

    /**
     * Constructor af a card.
     * 
     * @param character the {@code CharacterType} assigned
     * @param description the {@code String} of the proposal
     * @param refusal a {@code Decision} representing the consequences of the refusal
     * @param approval a {@code Decision} representing the consequences of the approval
     */
    public CardImpl(final CharacterType character, final String description, final Decision refusal, final Decision approval) {
        this.character = character;
        this.description = description;
        this.refusal = refusal;
        this.approval = approval;
        this.usage = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsed() {
        return this.usage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CharacterType getCharacter() {
        return this.character;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Decision getRefusal() {
        return this.refusal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Decision getApproval() {
        return this.approval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Effect> getAllEffects() {
        return List.of(refusal.getEffect1(), refusal.getEffect2(), approval.getEffect1(), approval.getEffect2());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeUsage() {
        this.usage = true;
    }

}
