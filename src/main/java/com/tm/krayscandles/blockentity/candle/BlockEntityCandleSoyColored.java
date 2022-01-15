package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.item.ItemCrystal;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleSoyColored extends BlockEntityCandleBase {

    public BlockEntityCandleSoyColored(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_SOY_COLORED.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {};
    }

    @Override
    public int getMaxCrystalCountOfType(ItemCrystal.CrystalType type) {
        return 0;
    }
}
