package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Effect;
import it.unibo.aurea.model.api.ParameterType;

public class EffectImpl implements Effect{
    private ParameterType parameter;
    private Integer delta;

    public EffectImpl(ParameterType parameter, Integer delta) {
        this.parameter = parameter;
        this.delta = delta;
    }

    @Override
    public ParameterType getParameter() {
        return this.parameter;
    }

    @Override
    public Integer getDelta() {
        return this.delta;
    }

}
