package it.unibo.aurea.model.api;

/**
 * Represents the possible characters in the game.  
 * Each of them has a specific positive influence on one parameter:
 * -STUDENT ->  STUDENTS
 * -MUM -> REPUTATION
 * -PROFESSOR -> PROFESSORS
 * -BUSINESSMAN -> FINANCES
 * 
 */
public enum CharacterType {
    STUDENT, 
    MUM, 
    PROFESSOR, 
    BUSINESSMAN
}
