package com.tm.krayscandles.block.base;

import com.mojang.math.Vector3d;
import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.item.ItemCrystal;
import com.tm.krayscandles.ritual.RitualResultItem;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import com.tm.krayscandles.util.helper.SoulHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;
import java.util.function.ToIntFunction;

/**
 * The base class for Candle Blocks.
 */
public abstract class BlockCandleBase extends BaseEntityBlock implements EntityBlock, RitualResultItem {

    /**
     * The state of the Candle being lit.
     */
    public static BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * The Candle's light emission value based on its LIT state.
     */
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (blockState) -> blockState.getValue(LIT) ? 14 : 0;

    public BlockCandleBase() {
        super(Block.Properties.of(Material.DECORATION, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.CANDLE).lightLevel(LIGHT_EMISSION).noOcclusion());
        registerDefaultState(getStateDefinition().any().setValue(LIT, false));
    }

    public BlockCandleBase(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(LIT, false));
    }


    // ABSTRACT METHODS \\


    /**
     * @return The collision and selection shape of the Candle.
     */
    public abstract VoxelShape getCandleShape(BlockState state);

    /**
     * @return The Block Entity Type of te Candle.
     */
    public abstract BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType();

    /**
     * Renders the flames being emitted when Candle is lit.
     */
    public abstract void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos);


    // STATE HELPER METHODS \\


    /**
     * @param state The BlockState of the Candle.
     * @return true, if the Candle is lit.
     */
    public static boolean isLit(BlockState state) {
        return state.getValue(LIT);
    }

    /**
     * Sets the Candles LIT value.
     * @param location The Location of the Candle.
     * @param value The new LIT value.
     */
    public static void setLit(Location location, boolean value) {
        location.setBlock(location.getBlockState().setValue(LIT, value));
    }

    /**
     * Called to light the Candle at set the Soul if needed.
     * @param location The Location of the Candle being lit.
     * @param player The Player that is lighting the candle. Can be null.
     * @param lighter The Item used to light the Candle.
     */
    public static InteractionResult lightCandle(Location location, Player player, ItemStack lighter) {

        boolean flintAndSteel = lighter.getItem() == Items.FLINT_AND_STEEL;
        boolean lantern = lighter.getItem() == Items.LANTERN;
        boolean soulLantern = lighter.getItem() == Items.SOUL_LANTERN;
        boolean trappedSoulLantern = lighter.getItem() == InitItems.LANTERN_SOUL_TRAPPED.get().asItem();

        //Checks if the Candle isn't already lit.
        if (!isLit(location.getBlockState())) {

            //Checks if the lighter is an accepted lighter item.
            if (flintAndSteel || lantern || soulLantern || trappedSoulLantern) {

                setLit(location, true);

                //Checks if the lighter is a flintAndSteel. If so, damage it.
                if (flintAndSteel) {
                    if (!location.level.isClientSide()) lighter.hurt(1, location.level.getRandom(), (ServerPlayer) player);
                    SoundHelper.playAtLocation(location, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1, 1);
                }

                else {

                    if (lighter.getItem().equals(InitItems.LANTERN_SOUL_TRAPPED.get().asItem())) {
                        SoulHelper.setSoulBlock(location, SoulHelper.getSoulStack(lighter));
                    }

                    SoundHelper.playAtLocation(location, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1, 1);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    /**
     * Called to extinguish the Candle and remove the attached Soul.
     * @param location The Location of the Candle.
     */
    public static void extinguishCandle(Location location) {

        //Checks the Candle is lit. If so, extinguish it.
        if (isLit(location.getBlockState())) {
            setLit(location, false);
            SoulHelper.removeSoulBlock(location);
            SoundHelper.playAtLocation(location, SoundEvents.CANDLE_EXTINGUISH, SoundSource.PLAYERS, 1, 2);
        }
    }

    /**
     * @param location The Location of the Candle.
     * @return the number of attached Crystals.
     */
    public static List<ItemCrystal.CrystalType> getCrystals(Location location) {

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleBase candle) {
            return candle.getCrystals();
        }

        return null;
    }

    /**
     * Adds an Amplifying Crystal to the Candle.
     * @param location The Location of the Candle.
     */
    public static InteractionResult insertCrystal(Location location, ItemStack crystal) {

        ItemCrystal.CrystalType crystalType = ItemCrystal.CrystalType.getCrystalTypeFromItem(crystal.getItem());

        if (crystalType != null) {

            if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

                if (candle.getCrystals().size() < candle.getMaxCrystalCount()) {

                    if (candle.getCrystalCountOfType(crystalType) < candle.getMaxCrystalCountOfType(crystalType)) {
                        candle.getCrystals().add(crystalType);
                        crystal.shrink(1);
                        SoundHelper.playAtLocation(location, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 1, 1);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }

        return InteractionResult.PASS;
    }

    /**
     * Removes a Crystal from the Candle.
     * @param location The Location of the Candle.
     */
    public static InteractionResult removeCrystal(Location location) {

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

            ItemCrystal.CrystalType crystalType = candle.getLastCrystalPlaced();

            if (crystalType != null) {
                candle.getCrystals().remove(candle.getCrystals().size() - 1);
                ItemHelper.spawnStackAtLocation(location.level, location, new ItemStack(crystalType.getItem()));
                SoundHelper.playAtLocation(location, SoundEvents.RESPAWN_ANCHOR_DEPLETE, SoundSource.PLAYERS, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }


    // BLOCK ACTION METHODS \\


    /**
     * Used to light the Candle when a Player right clicks it.
     */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        Location location = new Location(level, pos);
        ItemStack heldStack = player.getItemInHand(hand);

        if (heldStack.isEmpty() && player.isCrouching()) {
            return removeCrystal(location);
        }

        if (heldStack.getItem() instanceof ItemCrystal) {
            return insertCrystal(location, heldStack);
        }

        //Checks if the Candle is lit. If so, attempt to trap a Soul.
        if (isLit(state)) {

            if (!SoulHelper.getSoulBlock(location).isNull()) {

                if (heldStack.getItem() == InitItems.LANTERN_SOUL_TRAPPED.get().asItem() || heldStack.getItem() == Items.SOUL_LANTERN.asItem()) {
                    ItemStack soulTrappedLantern = new ItemStack(InitItems.LANTERN_SOUL_TRAPPED.get());
                    SoulHelper.setSoulStack(soulTrappedLantern, ((BlockEntitySoulHolder)location.getBlockEntity()).getSoul());
                    heldStack.shrink(1);
                    ItemHelper.spawnStackAtEntity(level, player, soulTrappedLantern);
                    return InteractionResult.SUCCESS;
                }
            }

            return InteractionResult.PASS;
        }

        //If not, attempt to light the Candle.
        else return lightCandle(location, player, heldStack);
    }

    /**
     * Used to extinguish the Candle when a Player left clicks it.
     */
    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        extinguishCandle(new Location(level, pos));
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {

        Location location = new Location(level, pos);

        if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

            for (ItemCrystal.CrystalType crystalType : candle.getCrystals()) {
                ItemHelper.spawnStack(level, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack(crystalType.getItem()));
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }


    // BLOCK BASE METHODS \\


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return getBlockEntityType().create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, getBlockEntityType(), BlockEntityCandleBase::tick);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {

        LocalPlayer player = Minecraft.getInstance().player;
        Location location = new Location(level, pos);

        if (isLit(state)) {
            renderFlame(level, pos, state, new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D));

            if (rand.nextFloat() < 0.17F) {
                SoundHelper.playAtLocationLocal(location, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F);
            }

            if (player.getInventory().getArmor(3).getItem() == InitItems.BLESSED_NIGHT_MASK.get()) {

                if (location.getBlockEntity() != null && location.getBlockEntity() instanceof BlockEntityCandleBase candle) {

                    if (candle.getMaxCrystalCountOfType(ItemCrystal.CrystalType.AMPLIFYING) != 0) {
                        CandleParticleHelper.renderCandleAura(location);
                    }
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return LIGHT_EMISSION.applyAsInt(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getCandleShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getCandleShape(state);
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.PUSH_ONLY;
    }
}
