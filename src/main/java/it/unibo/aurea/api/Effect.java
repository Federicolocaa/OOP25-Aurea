package it.unibo.aurea.api;

/** 
 * Represents which and how a parameter is modified.
 */
public interface Effect {

    /**
     * Indicates the parameter matched with this effect.
     * 
     * @return the {@code Parameter} the effect is on
     */
    Parameter getParameter();

    /**
     * Indicates the impact of this effect.
     * 
     * @return an {@code Integer} to add at the level of the parameter
     */
    Integer getDelta();
}
