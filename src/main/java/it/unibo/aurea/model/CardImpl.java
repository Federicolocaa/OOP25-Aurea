package it.unibo.aurea.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.aurea.model.api.Card;
import it.unibo.aurea.model.api.CharacterType;
import it.unibo.aurea.model.api.Effect;
import it.unibo.aurea.model.api.ParameterType;

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
     * Constructor of a {@code Card} using the Builder pattern.
     * 
     * @param builder the builder of a new card
     */
    public CardImpl(final Builder builder) {
        this.character = builder.character;
        this.description = builder.description;
        this.refusal = new Decision(builder.textRefusal, builder.refEffect1, builder.refEffect2);
        this.approval = new Decision(builder.textApproval, builder.appEffect1, builder.appEffect2);
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
        final List<Effect> allEffects = new ArrayList<>();
        allEffects.addAll(refusal.getEffects());
        allEffects.addAll(approval.getEffects());
        return allEffects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeUsage() {
        this.usage = true;
    }

    /**
     * Builder class used to construct Card instances in a clear way.
     * It allows step-by-step setting of all card attributes (character, description,
     * approval/refusal texts, and their effects).
     */
    public static class Builder {
        private CharacterType character;
        private String description;
        private String textRefusal;
        private Effect refEffect1;
        private Effect refEffect2;
        private String textApproval;
        private Effect appEffect1;
        private Effect appEffect2;

        /**
         * Sets the character associated with the card.
         * 
         * @param charact the character type
         * @return this builder
         */
        public Builder character(final CharacterType charact) {
            this.character = charact;
            return this;
        }

        /**
         * Sets the description of the card.
         * 
         * @param text the card description
         * @return this builder
         */
        public Builder description(final String text) {
            this.description = text;
            return this;
        }

        /**
         * Sets the refusal decision text.
         * 
         * @param text the refusal text
         * @return this builder
         */
        public Builder textRefusal(final String text) {
            this.textRefusal = text;
            return this;
        }

        /**
         * Adds an effect to the refusal decision.
         * 
         * @param param the affected parameter
         * @param delta the effect value
         * @return this builder
         */
        public Builder effectRefusal(final ParameterType param, final int delta) {
            if (Objects.isNull(refEffect1)) {
                this.refEffect1 = new EffectImpl(param, delta);
            } else {
                this.refEffect2 = new EffectImpl(param, delta);
            }
            return this;
        }

        /**
         * Sets the approval decision text.
         * 
         * @param text the approval text
         * @return this builder
         */
        public Builder textApproval(final String text) {
            this.textApproval = text;
            return this;
        }

        /**
         * Adds an effect to the approval decision.
         * 
         * @param param the affected parameter
         * @param delta the effect value
         * @return this builder
         */
        public Builder effectApproval(final ParameterType param, final int delta) {
            if (Objects.isNull(appEffect1)) {
                this.appEffect1 = new EffectImpl(param, delta);
            } else {
                this.appEffect2 = new EffectImpl(param, delta);
            }
            return this;
        }

        /**
         * Builds and returns the Card instance.
         * 
         * @return the constructed card
         */
        public Card build() {
            return new CardImpl(this);
        }
    }

}
