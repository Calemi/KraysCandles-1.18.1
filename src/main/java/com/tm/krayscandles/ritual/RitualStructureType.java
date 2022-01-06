package com.tm.krayscandles.ritual;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The general structure of a ritual.
 */
public class RitualStructureType {

    private final String name;
    private SoundEvent sound;
    private Supplier<SoundEvent> soundSupplier;

    private final List<RitualBlock> ritualBlocks = new ArrayList<>();

    /**
     * Creates a ritual structure.
     * @param name The name of the structure.
     * @param sound The sound that plays when the ritual is performed.
     */
    public RitualStructureType(String name, SoundEvent sound) {
        this.name = name;
        this.sound = sound;
    }

    /**
     * Creates a ritual structure.
     * @param name The name of the structure.
     * @param soundSupplier The sound that plays when the ritual is performed.
     */
    public RitualStructureType(String name, Supplier<SoundEvent> soundSupplier) {
        this.name = name;
        this.soundSupplier = soundSupplier;
    }

    public String getName() {
        return name;
    }

    public SoundEvent getSound() {

        if (soundSupplier != null) {
            return soundSupplier.get();
        }

        return sound;
    }

    /**
     * @return A list of all required Ritual Blocks for the structure.
     */
    public List<RitualBlock> getRitualBlocks() {
        return ritualBlocks;
    }

    /**
     * Adds a ritual block ingredient to the structure.
     * @param block The ritual block to add.
     */
    public void addRitualBlock(RitualBlock block) {
        getRitualBlocks().add(block);
    }

    /**
     * Adds 4 ritual block ingredients that are symmetrical around the origin of the ritual.
     * @param block The ritual block to add.
     */
    public void addSymmetricalRitualBlock(RitualBlock block) {
        getRitualBlocks().add(block);
        getRitualBlocks().add(block.rotate(Rotation.CLOCKWISE_90));
        getRitualBlocks().add(block.rotate(Rotation.COUNTERCLOCKWISE_90));
        getRitualBlocks().add(block.rotate(Rotation.CLOCKWISE_180));
    }

    /**
     * @return The ritual structure but rotated 90 degrees.
     */
    public RitualStructureType getRotatedRitual() {

        RitualStructureType structure = new RitualStructureType(name, sound);

        for (RitualBlock ritualBlock : getRitualBlocks()) {
            structure.addRitualBlock(ritualBlock.rotate(Rotation.CLOCKWISE_90));
        }

        return structure;
    }

    /**
     * @param level The level the ritual is in.
     * @param origin The origin of the ritual.
     * @param structure The structure to check.
     * @param checkSymmetry Should it check symmetry.
     * @return true, if the all required ingredients are present and valid.
     */
    private boolean isStructureComplete(Level level, BlockPos origin, RitualStructureType structure, boolean checkSymmetry) {

        for (RitualBlock ritualBlock : structure.getRitualBlocks()) {

            if (!ritualBlock.isValid(level, origin)) {

                if (checkSymmetry) {
                    return structure.isStructureComplete(level, origin, getRotatedRitual(), false);
                }

                return false;
            }
        }

        return true;
    }

    /**
     * @param level The level the ritual is in.
     * @param origin The origin of the ritual.
     * @return true, if the all required ingredients are present and valid.
     */
    public boolean isStructureComplete(Level level, BlockPos origin) {
        return isStructureComplete(level, origin, this, true);
    }
}
