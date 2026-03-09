package it.unibo.aurea.model.api;

// DA RIVEDERE

/**
 * Deck of the possible cards of the games. 
 * 1->18 bonus finance
 * 19->36 bonus student
 * 37->54 bonus professor
 * 55->72 bonus reputation
 */
public enum Cards {
    APPLE (1),
    GREEN(19), 
    SALARY(37),
    HOUSES(55);

    private Integer value;

    private Cards(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }



}
