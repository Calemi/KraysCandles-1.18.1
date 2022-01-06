package com.tm.krayscandles.blockentity.base;

import com.tm.calemicore.util.helper.EffectHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.soul.ITrappedSoul;
import com.tm.krayscandles.soul.Soul;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

/**
 * The base class for Candle Block Entities.
 */
public abstract  class BlockEntityCandleBase extends BlockEntityBase implements ITrappedSoul {

    /**
     * The range of the Candle's effects.
     */
    public static final int EFFECT_RANGE = 8;

    /**
     * The trapped Soul within the Candle.
     */
    private Soul trappedSoul;

    public BlockEntityCandleBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        trappedSoul = new Soul(null);
    }

    /**
     * @return The effects of the Candle to apply when lit.
     */
    public abstract MobEffectInstance[] getCandleEffects();

    /**
     * Called when an entity is affected by this Candle.
     * @param entity The entity affected.
     */
    public void onEntityEffect(LivingEntity entity) {}

    /**
     * @return The trapped Soul within the Candle.
     */
    @Override
    public Soul getSoul() {
        return trappedSoul;
    }

    /**
     * Sets the current trapped soul.
     * @param soul The soul to set
     */
    @Override
    public void setSoul(Soul soul) {
        this.trappedSoul = soul;
    }

    /**
     * Called every tick.
     * Handles applying the Candle's effects on entities near it.
     */
    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityCandleBase blockEntity) {

        if (level != null) {

            if (level.getGameTime() % 20 == 0) {

                if (state.getBlock() instanceof BlockCandleBase && state.getValue(BlockCandleBase.LIT)) {

                    List<Entity> entities = level.getEntities(null, new AABB(pos.getX() - EFFECT_RANGE, pos.getY() - EFFECT_RANGE, pos.getZ() - EFFECT_RANGE, pos.getX() + EFFECT_RANGE, pos.getY() + EFFECT_RANGE, pos.getZ() + EFFECT_RANGE));

                    for (Entity entity : entities) {

                        if (entity instanceof LivingEntity livingEntity) {

                            EntityType<?> type = blockEntity.getSoul().getEntity();

                            if (type == null || livingEntity.getType().equals(type)) {

                                for (MobEffectInstance effect : blockEntity.getCandleEffects()) {
                                    EffectHelper.addPotionEffect(effect.getEffect(), 60, effect.getAmplifier(), livingEntity);
                                }

                                blockEntity.onEntityEffect(livingEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates this Block Entity from a CompoundTag when loaded.
     * @param tag The CompoundTag to load from.
     */
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        trappedSoul = Soul.load(tag);
    }

    /**
     * Saves this Block Entity to a CompoundTag when saved.
     * @param tag The CompoundTag to save to.
     */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        trappedSoul.save(tag);
    }
}
