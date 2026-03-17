package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Parameter;

public class ParameterImpl implements Parameter{
    public static final int START_LEVEL = 50;
    public static final int MIN_LEVEL = 0;
    public static final int MAX_LEVEL = 100;

    private String name;
    private int level;
    private boolean alive;

    public ParameterImpl(String name) {
        this.name = name.toUpperCase();
        this.level = START_LEVEL;
        this.alive = true;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void modify(Integer delta) {
        this.level += delta;
        if (this.level <= MIN_LEVEL || this.level >= MAX_LEVEL) {
            this.alive = false;
        }
    }

    @Override
    public boolean isAlive() {
       return this.alive;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
