package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleWaxEar extends BlockEntityCandleBase {

    public BlockEntityCandleWaxEar(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_WAX_EAR.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {};
    }
}
