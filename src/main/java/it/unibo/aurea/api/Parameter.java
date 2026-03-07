package it.unibo.aurea.api;

/**
 * It represents the parameter of the game. The level of each parameter MUST BE > 0 && < 100, otherwise game over.
 */
public enum Parameter {
    FINANCE("Finance"),
    STUDENT("Student"),
    PROFESSOR("Professor"),
    REPUTATION("Reputation");

    private String name;
    private Integer level;
    private boolean liveness;

    Parameter(String name) {
        this.name = name;
        this.level = KeyNumber.START_LEVEL.getValue();
        this.liveness = true;
    }

    /**
     * Indicates the level of the current parameter.
     * 
     * @return the number between 0/100
     */
    public Integer level() {
        return this.level;
    }

    /**
     * It performs the raises or decreases of the level of this parameter. Based on the level, it modify the liveness of this parameter.
     * 
     * @param delta the amount to add/subtract based on the sign.
     */
    public void modify(Integer delta) {
        this.level += delta;
        if (this.level <= KeyNumber.MIN.getValue() || this.level >= KeyNumber.MAX.getValue()) {
            this.liveness = false;
        }
    }

    /**
     * Indicates if the player can continue the game based on this parameter.
     * 
     * @return {@code true} if is > 0 && < 100, {@code false} otherwise
     */
    public boolean isAlive() {
        return this.liveness;
    }

    public String getName() {
        return name;
    }
    
}
