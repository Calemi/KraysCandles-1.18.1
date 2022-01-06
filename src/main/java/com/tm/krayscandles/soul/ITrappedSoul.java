package com.tm.krayscandles.soul;

/**
 * Used by Block Entities that contain a soul flame.
 */
public interface ITrappedSoul {

    /**
     * @return The soul of the flame.
     */
    Soul getSoul();

    /**
     * Sets the current trapped soul.
     * @param soul The soul to set
     */
    void setSoul(Soul soul);
}
