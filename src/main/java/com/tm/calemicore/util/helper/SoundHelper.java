package com.tm.calemicore.util.helper;

import com.tm.calemicore.util.Location;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Use this class to play sounds.
 */
public class SoundHelper {

    /**
     * Plays a sound at a Location.
     * @param location The Location to play the sound at.
     * @param sound The sound to play.
     * @param source The source of the sound.
     * @param volume The volume of the sound.
     * @param pitch The pitch of the sound.
     */
    public static void playAtLocation(Location location, SoundEvent sound, SoundSource source, float volume, float pitch) {
        location.level.playSound(null, location.getBlockPos(), sound, source, volume, pitch);
    }

    /**
     * Plays a sound at a Player.
     * @param player The Player to play the sound at.
     * @param sound The sound to play.
     * @param source The source of the sound.
     * @param volume The volume of the sound.
     * @param pitch The pitch of the sound.
     */
    public static void playAtPlayer(Player player, SoundEvent sound, SoundSource source, float volume, float pitch) {
        player.getLevel().playSound(player, new BlockPos(player.getBlockX(), player.getBlockY(), player.getZ()), sound, source, volume, pitch);
    }

    /**
     * Plays a sound at a Player with:
     * SoundSource = PLAYERS
     * volume = 1
     * pitch = 1
     * @param player The Player to play the sound at.
     * @param sound The sound to play.
     */
    public static void playSimple(Player player, SoundEvent sound) {
        playAtPlayer(player, sound, SoundSource.PLAYERS, 1, 1);
    }

    /**
     * Players a Block's placing sound at a Location.
     * @param location The Location to play the sound at.
     * @param state The BlockState of the placed Block.
     * @param player The Player who placed the Block.
     */
    public static void playBlockPlace(Location location, BlockState state, Player player) {
        player.getLevel().playSound(player, location.getBlockPos(), state.getSoundType(player.getLevel(), location.getBlockPos(), player).getPlaceSound(), SoundSource.NEUTRAL, 1F, 1F);
    }

    /**
     * Players a Block's break sound at a Location.
     * @param location The Location to play the sound at.
     * @param state The BlockState of the broke Block.
     * @param player The Player who broke the Block.
     */
    public static void playBlockBreak(Location location, BlockState state, Player player) {
        player.getLevel().playSound(player, location.getBlockPos(), state.getSoundType(player.getLevel(), location.getBlockPos(), player).getBreakSound(), SoundSource.NEUTRAL, 1F, 1F);
    }
}
