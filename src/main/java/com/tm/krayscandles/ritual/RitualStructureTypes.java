package com.tm.krayscandles.ritual;

import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.init.InitSounds;
import net.minecraft.sounds.SoundEvents;

public class RitualStructureTypes {

    public static final RitualStructureType ESSENCE = new RitualStructureType("Essence", SoundEvents.CONDUIT_ACTIVATE);
    public static final RitualStructureType RUNE = new RitualStructureType("Rune", InitSounds.RUNE_RITUAL);
    public static final RitualStructureType CANDLE = new RitualStructureType("Candle", SoundEvents.CONDUIT_ACTIVATE);
    public static final RitualStructureType WAND = new RitualStructureType("Wand", InitSounds.WAND_RITUAL);
    public static final RitualStructureType WRAITH = new RitualStructureType("Wraith", InitSounds.WRAITH_RITUAL);

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

        WAND.addRitualBlock(new RitualBlockAltar());
        WAND.addSymmetricalRitualBlock(new RitualBlockAltar(2, 0, 0));
        WAND.addSymmetricalRitualBlock(new RitualBlockCandleSoy(1, 0, 1));

//        WRAITH.addRitualBlock(new RitualBlockCandle(InitItems.CANDLE_CURSED.get()));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy(0, 0, -2));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy( 0, 0, 2));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, -1));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, -1));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy(2, 0, 1));
//        WRAITH.addRitualBlock(new RitualBlockCandleSoy(-2, 0, 1));
    }
}
