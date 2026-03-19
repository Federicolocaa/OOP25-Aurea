package it.unibo.aurea.model;

import it.unibo.aurea.model.api.Parameter;
import it.unibo.aurea.model.api.ParameterType;

/**
 * {@inheritDoc}.
 */
public final class ParameterImpl implements Parameter {

    /** Start level constant. */
    public static final int START_LEVEL = 50;
    /** Min level constant. */
    public static final int MIN_LEVEL = 0;
    /** Max level constant. */
    public static final int MAX_LEVEL = 100;

    private final ParameterType name;
    private int level;
    private boolean alive;

    /**
     * Constructor of a specific parameter.
     *
     * @param name the name of a {@code ParameterType}
     */
    public ParameterImpl(final ParameterType name) {
        this.name = name;
        this.level = START_LEVEL;
        this.alive = true;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void modify(final int delta) {
        this.level += delta;
        if (this.level >= MAX_LEVEL) {
            this.level = MAX_LEVEL;
            this.alive = false;
        } else if (this.level <= MIN_LEVEL) {
            this.level = MIN_LEVEL;
            this.alive = false;
        }
    }

    @Override
    public boolean isAlive() {
       return this.alive;
    }

    @Override
    public ParameterType getName() {
        return this.name;
    }

}
