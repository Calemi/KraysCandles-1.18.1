package com.tm.krayscandles.soul;

/**
 * Used by Block Entities that contain a soul.
 */
public interface BlockEntitySoulHolder {

    /**
     * @return The soul.
     */
    Soul getSoul();

    /**
     * Sets the current trapped soul.
     * @param soul The soul to set
     */
    void setSoul(Soul soul);

    /**
     * Removes the current trapped soul.
     */
    void removeSoul();
}
