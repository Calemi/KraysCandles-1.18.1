package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.item.ItemCrystal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleCavern extends BlockEntityCandleBase {

    public BlockEntityCandleCavern(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_CAVERN.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {new MobEffectInstance(MobEffects.DIG_SPEED)};
    }

    @Override
    public int getMaxCrystalCountOfType(ItemCrystal.CrystalType type) {

        switch (type) {
            case AMPLIFYING, POTENCY -> {
                return getMaxCrystalCount();
            }
            case INVERTING -> {
                return 1;
            }
        }

        return 0;
    }
}
