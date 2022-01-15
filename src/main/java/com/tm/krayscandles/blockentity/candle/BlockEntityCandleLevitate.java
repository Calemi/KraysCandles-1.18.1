package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.item.ItemCrystal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleLevitate  extends BlockEntityCandleBase {

    public BlockEntityCandleLevitate(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_LEVITATE.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {new MobEffectInstance(MobEffects.LEVITATION)};
    }

    @Override
    public int getMaxCrystalCountOfType(ItemCrystal.CrystalType type) {

        switch (type) {
            case AMPLIFYING -> {
                return getMaxCrystalCount();
            }
            case POTENCY -> {
                return 0;
            }
            case INVERTING -> {
                return 1;
            }
        }

        return 0;
    }
}

