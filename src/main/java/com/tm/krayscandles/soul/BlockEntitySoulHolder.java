package com.tm.krayscandles.soul;

/**
 * Used by Block Entities that contain a Soul.
 */
public interface BlockEntitySoulHolder {

    /**
     * @return The Soul.
     */
    Soul getSoul();

    /**
     * Sets the current trapped Soul.
     * @param soul The Soul to set
     */
    void setSoul(Soul soul);

    /**
     * Removes the current trapped Soul.
     */
    void removeSoul();
}
