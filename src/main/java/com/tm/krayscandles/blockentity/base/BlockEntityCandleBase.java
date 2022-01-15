package com.tm.krayscandles.blockentity.base;

import com.tm.calemicore.util.helper.MobEffectHelper;
import com.tm.krayscandles.block.base.BlockCandleBase;
import com.tm.krayscandles.config.KCConfig;
import com.tm.krayscandles.item.ItemCrystal;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The base class for Candle Block Entities.
 */
public abstract class BlockEntityCandleBase extends BlockEntityBase implements BlockEntityCrystalHolder, BlockEntitySoulHolder {

    /**
     * The range of the Candle's effects.
     */
    private final int startingEffectRange = KCConfig.candles.candleEffectRangeStart.get();

    /**
     * The Crystals attached to the Candle.
     */
    private final List<ItemCrystal.CrystalType> crystals = new ArrayList<>();

    /**
     * The trapped Soul within the Candle.
     */
    private Soul trappedSoul;

    public BlockEntityCandleBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        removeSoul();
    }

    /**
     * The range of the Candle's effects.
     */
    public int getEffectRange() {
        return startingEffectRange + (getCrystalCountOfType(ItemCrystal.CrystalType.AMPLIFYING) * KCConfig.candles.candleEffectRangeAdd.get());
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
     * @return The Crystals attached to the Candle.
     */
    public List<ItemCrystal.CrystalType> getCrystals() {
        return crystals;
    }

    /**
     * @param crystalType The Crystal type to search for.
     * @return The current amount of a type of Crystal attached to the Candle.
     */
    public int getCrystalCountOfType(ItemCrystal.CrystalType crystalType) {

        int count = 0;

        for (ItemCrystal.CrystalType crystal : getCrystals()) {
            if (crystal == crystalType) count++;
        }

        return count;
    }

    /**
     * @return The last Crystal attached to the Candle. Returns null if there are none.
     */
    public ItemCrystal.CrystalType getLastCrystalPlaced() {

        if (!getCrystals().isEmpty()) {
            return getCrystals().get(getCrystals().size() - 1);
        }

        return null;
    }

    /**
     * @return The maximum amount of Crystals (disregarding the type) the Block Entity can hold.
     */
    @Override
    public int getMaxCrystalCount() {
        return 4;
    }

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
     * Removes the current trapped soul.
     */
    @Override
    public void removeSoul() {
        this.trappedSoul = new Soul(null);
    }

    /**
     * Called every tick.
     * Handles applying the Candle's effects on entities near it.
     */
    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntityCandleBase candle) {

        if (level != null) {

            if (level.getGameTime() % 20 == 0) {

                if (state.getBlock() instanceof BlockCandleBase && state.getValue(BlockCandleBase.LIT)) {

                    int effectRange = candle.getEffectRange();

                    List<Entity> entities = level.getEntities(null, new AABB(pos.getX() - effectRange, pos.getY() - effectRange, pos.getZ() - effectRange, pos.getX() + effectRange + 1, pos.getY() + effectRange + 1, pos.getZ() + effectRange + 1));

                    for (Entity entity : entities) {

                        if (entity instanceof LivingEntity livingEntity) {

                            EntityType<?> type = candle.getSoul().getEntity();

                            boolean inverted = candle.getCrystalCountOfType(ItemCrystal.CrystalType.INVERTING) > 0;

                            if (type == null || (!inverted && livingEntity.getType().equals(type)) || (inverted && !livingEntity.getType().equals(type))) {

                                for (MobEffectInstance effect : candle.getCandleEffects()) {
                                    MobEffectHelper.addMobEffect(effect.getEffect(), 60, effect.getAmplifier() + candle.getCrystalCountOfType(ItemCrystal.CrystalType.POTENCY), livingEntity);
                                }

                                candle.onEntityEffect(livingEntity);
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

        CompoundTag crystalTag = tag.getCompound("Crystal");

        for (int i = 0; i < getMaxCrystalCount(); i++) {
            if (crystalTag.contains("crystal_" + i)) crystals.set(i, ItemCrystal.CrystalType.getCrystalTypeFromId(crystalTag.getInt("crystal_" + i)));
        }

        trappedSoul = Soul.load(tag);
    }

    /**
     * Saves this Block Entity to a CompoundTag when saved.
     * @param tag The CompoundTag to save to.
     */
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        CompoundTag crystalTag = new CompoundTag();
        for (int i = 0; i < crystals.size(); i++) {
            crystalTag.putInt("crystal_" + i, crystals.get(i).getId());
        }
        tag.put("Crystal", crystalTag);

        trappedSoul.save(tag);
    }
}
