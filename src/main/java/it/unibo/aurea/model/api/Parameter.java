package it.unibo.aurea.model.api;

/** 
 * Represents the structure of the four parameter of the game.
 * 
 */
public interface Parameter {
    /**
     * Indicates the level of the current parameter.
     * 
     * @return an {@code int} between 0/100
     */
    int getLevel();

    /**
     * Performs the raises or decreases of the level of this parameter. Based on the level it can modify the life of this parameter.
     * 
     * @param delta the amount to add/subtract based on the sign.
     */
    void modify(Integer delta);

    /**
     * Indicates if the player can continue the game based on this parameter.
     * 
     * @return {@code true} if is > 0 && < 100, {@code false} otherwise
     */
    boolean isAlive();

    /**  
     * Indicates the name of the current parameter.
     * 
     * @return the name of the parameter in UPPER_CASE
     */
    String getName();
}

