package com.tm.krayscandles.blockentity.candle;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.item.ItemCrystal;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityCandleNull extends BlockEntityCandleBase {

    private final List<Location> area = new ArrayList<>();

    public int lastCrystalCount;

    public BlockEntityCandleNull(BlockPos pos, BlockState state) {
        super(InitBlockEntityTypes.CANDLE_NULL.get(), pos, state);
        lastCrystalCount = getCrystals().size();
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

        area.clear();

        for (int x = -getEffectRange(); x <= getEffectRange(); x++) {
            for (int y = -getEffectRange(); y <= getEffectRange(); y++) {
                for (int z = -getEffectRange(); z <= getEffectRange(); z++) {
                    area.add(new Location(getLevel(), getLocation().x + x, getLocation().y + y, getLocation().z + z));
                }
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityCandleNull candle) {

        BlockEntityCandleBase.tick(level, pos, state, candle);

        if (candle.area.isEmpty() || candle.lastCrystalCount != candle.getCrystals().size()) {
            candle.lastCrystalCount = candle.getCrystals().size();
            candle.setArea();
            LogHelper.log(KCReference.MOD_NAME, "reset");
        }

        if (level != null) {

            if (level.getGameTime() % 20 == 0) {

                if (state.getBlock() instanceof BlockCandleBase && state.getValue(BlockCandleBase.LIT)) {

                    for (Location location : candle.area) {

                        if (location.getBlock() instanceof BlockCandleBase && !(location.getBlockEntity() instanceof BlockEntityCandleNull)) {
                            BlockCandleBase.extinguishCandle(location);
                        }
                    }
                }
            }
        }
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
