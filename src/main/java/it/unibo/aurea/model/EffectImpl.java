package it.unibo.aurea.model;

import it.unibo.aurea.api.Effect;
import it.unibo.aurea.api.Parameter;

public class EffectImpl implements Effect{
    private Parameter parameter;
    private Integer delta;

    public EffectImpl(Parameter parameter, Integer delta) {
        this.parameter = parameter;
        this.delta = delta;
    }

    public Parameter getParameter() {
        return this.parameter;
    }

    public Integer getDelta() {
        return this.delta;
    }
}
