package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Effect;
import it.unibo.aurea.model.api.ParameterType;

/**
 * {@inheritDoc}.
 */
public class EffectImpl implements Effect {
    private final ParameterType parameter;
    private final int delta;

    /**
     * Constructor af an effect.
     * 
     * @param parameter the {@code ParameterType} influenced
     * @param delta the {@code int} of how the parameter changes
     */
    public EffectImpl(final ParameterType parameter, final int delta) {
        this.parameter = parameter;
        this.delta = delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParameterType getParameter() {
        return this.parameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDelta() {
        return this.delta;
    }

}
