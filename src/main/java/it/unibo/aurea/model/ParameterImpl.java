package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Parameter;
import it.unibo.aurea.model.api.ParameterType;

/**
 * {@inheritDoc}.
 */
public class ParameterImpl implements Parameter {
    public static final int START_LEVEL = 50;
    public static final int MIN_LEVEL = 0;
    public static final int MAX_LEVEL = 100;

    private final ParameterType name;
    private int level;
    private boolean alive;

    /**
     * Constructor of a specifc parameter.
     * 
     * @param name the name of a {@code ParameterType}
     */
    public ParameterImpl(final ParameterType name) {
        this.name = name;
        this.level = START_LEVEL;
        this.alive = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modify(final int delta) {
        this.level += delta;
        if (this.level <= MIN_LEVEL || this.level >= MAX_LEVEL) {
            this.alive = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
       return this.alive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParameterType getName() {
        return this.name;
    }

}
