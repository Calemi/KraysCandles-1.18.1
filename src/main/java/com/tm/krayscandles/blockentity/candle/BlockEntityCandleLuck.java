package com.tm.krayscandles.blockentity.candle;

import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityCandleLuck extends BlockEntityCandleBase {

    public BlockEntityCandleLuck(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_LUCK.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {new MobEffectInstance(MobEffects.LUCK)};
    }
}
