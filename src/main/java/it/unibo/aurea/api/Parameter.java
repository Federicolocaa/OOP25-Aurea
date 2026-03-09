package it.unibo.aurea.api;

/** 
 * Represents the structure of the four parameter of the game: FINANCE, STUDENT, PROFESSOR, REPUTATION 
 */
public interface Parameter {
    /**
     * Indicates the level of the current parameter.
     * 
     * @return the number between 0/100
     */
    Integer level();

    /**
     * It performs the raises or decreases of the level of this parameter. Based on the level, it modify the liveness of this parameter.
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
     * Indicates the current parameter.
     * 
     * @return the name of the parameter in UPPER_CASE
     */
    String name();
}

