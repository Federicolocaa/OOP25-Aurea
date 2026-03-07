package it.unibo.aurea.model;

import java.lang.reflect.Parameter;

import it.unibo.aurea.api.Effect;

public class EffectImpl implements Effect{
    private Parameter parameter;
    private Integer delta;

    public EffectImpl(Parameter parameter, Integer delta) {
        this.parameter = parameter;
        this.delta = delta;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public Integer getDelta() {
        return delta;
    }
}
