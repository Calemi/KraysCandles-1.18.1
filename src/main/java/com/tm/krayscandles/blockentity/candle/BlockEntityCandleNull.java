package com.tm.krayscandles.blockentity.candle;

import com.tm.calemicore.util.Location;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityCandleNull extends BlockEntityCandleBase {

    private final List<Location> area = new ArrayList<>();

    public BlockEntityCandleNull(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_NULL.get(), pos, state);
    }

    @Override
    public MobEffectInstance[] getCandleEffects() {
        return new MobEffectInstance[] {};
    }

    @Override
    public void onEntityEffect(LivingEntity entity) {
        entity.removeAllEffects();
    }

    private void setArea() {

        for (int x = -EFFECT_RANGE; x <= EFFECT_RANGE; x++) {
            for (int y = -EFFECT_RANGE; y <= EFFECT_RANGE; y++) {
                for (int z = -EFFECT_RANGE; z <= EFFECT_RANGE; z++) {
                    area.add(new Location(getLevel(), getLocation().x + x, getLocation().y + y, getLocation().z + z));
                }
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityCandleNull blockEntity) {

        BlockEntityCandleBase.tick(level, pos, state, blockEntity);

        if (blockEntity.area.isEmpty()) {
            blockEntity.setArea();
        }

        if (level != null) {

            if (level.getGameTime() % 20 == 0) {

                if (state.getBlock() instanceof BlockCandleBase && state.getValue(BlockCandleBase.LIT)) {

                    for (Location location : blockEntity.area) {

                        if (location.getBlock() instanceof BlockCandleBase && !(location.getBlockEntity() instanceof BlockEntityCandleNull)) {
                            BlockCandleBase.extinguishCandle(location);
                        }
                    }
                }
            }
        }
    }
}
