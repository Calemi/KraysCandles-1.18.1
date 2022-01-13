package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.init.InitMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleBlessed extends BlockEntityCandleBase {

    public BlockEntityCandleBlessed(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_BLESSED.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {new MobEffectInstance(InitMobEffects.FLIGHT.get())};
    }
}
