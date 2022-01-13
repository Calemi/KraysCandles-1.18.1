package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleWaxBee extends BlockEntityCandleBase {

    public BlockEntityCandleWaxBee(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_WAX_BEE.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {};
    }
}
