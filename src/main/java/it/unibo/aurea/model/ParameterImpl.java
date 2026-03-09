package it.unibo.aurea.model;

import it.unibo.aurea.api.KeyNumber;
import it.unibo.aurea.api.Parameter;

public class ParameterImpl implements Parameter{
    private String name;
    private Integer level;
    private boolean liveness;

    public ParameterImpl(String name) {
        this.name = name.toUpperCase();
        this.level = KeyNumber.START_LEVEL.getValue();
        this.liveness = true;
    }

    @Override
    public Integer level() {
        return this.level;
    }

    @Override
    public void modify(Integer delta) {
        this.level += delta;
        if (this.level <= KeyNumber.MIN.getValue() || this.level >= KeyNumber.MAX.getValue()) {
            this.liveness = false;
        }
    }

    @Override
    public boolean isAlive() {
       return this.liveness;
    }

    @Override
    public String name() {
        return name;
    }

}
