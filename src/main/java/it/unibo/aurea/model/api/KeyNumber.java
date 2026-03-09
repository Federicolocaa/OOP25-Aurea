package it.unibo.aurea.model.api;

/** 
 * It contains the usefull number about the level of a {@code Parameter}.
 */
public enum KeyNumber {
    START_LEVEL(50),
    MIN(0),
    MAX(100);

    private final Integer value;

    KeyNumber(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
