package com.tm.krayscandles.ritual;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.sounds.SoundEvents;

public class RitualStructureTypes {

    public static final RitualStructureType ESSENCE = new RitualStructureType("essence", SoundEvents.CONDUIT_ACTIVATE);
    public static final RitualStructureType RUNE = new RitualStructureType("rune", InitSounds.RUNE_RITUAL);
    public static final RitualStructureType CANDLE = new RitualStructureType("candle", SoundEvents.CONDUIT_ACTIVATE);
    public static final RitualStructureType CRYSTAL = new RitualStructureType("crystal", SoundEvents.CONDUIT_ACTIVATE);
    public static final RitualStructureType WAND = new RitualStructureType("wand", InitSounds.WAND_RITUAL);
    public static final RitualStructureType WRAITH = new RitualStructureType("wraith", InitSounds.WRAITH_RITUAL);

    public static void init() {

        ESSENCE.addRitualBlock(new RitualBlockCandle(InitItems.CANDLE_WAX_BEE.get()));
        ESSENCE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 1));

        RUNE.addRitualBlock(new RitualBlock(InitItems.STATUE.get()));
        RUNE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(2, 0, 2));
        RUNE.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 0));

        CANDLE.addRitualBlock(new RitualBlockCandleSoy());
        CANDLE.addSymmetricalRitualBlock(new RitualBlockAltar(3, 0, 0));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 2));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(3, 0, 1));
        CANDLE.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 3));

        CRYSTAL.addRitualBlock(new RitualBlock(InitItems.CANDLE_BLESSED.get()));
        CRYSTAL.addSymmetricalRitualBlock(new RitualBlockAltar(1, 0, 0));
        CRYSTAL.addSymmetricalRitualBlock(new RitualBlockCandle(InitItems.CANDLE_ENERGY.get(), 2, 0, 0));
        CRYSTAL.addSymmetricalRitualBlock(new RitualBlockCandleSoy(2, 0, 2));

        WAND.addRitualBlock(new RitualBlockAltar());
        WAND.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 0));
        WAND.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 1));

        WRAITH.addRitualBlock(new RitualBlockCandle(InitItems.CANDLE_CURSED.get()));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(0, 0, -2));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy( 0, 0, 2));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, -1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, -1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, 1));
        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, 1));
    }
}
