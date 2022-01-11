package com.tm.krayscandles.block.base;

import com.mojang.math.Vector3d;
import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import com.tm.krayscandles.init.InitItems;
import com.tm.krayscandles.ritual.RitualResultItem;
import com.tm.krayscandles.soul.BlockEntitySoulHolder;
import com.tm.krayscandles.util.helper.SoulHelper;
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

    /**
     * @return The collision and selection shape of the Candle.
     */
    public abstract VoxelShape getCandleShape(BlockState state);

    public abstract BlockEntityType<? extends BlockEntityCandleBase> getBlockEntityType();

    /**
     * Renders the flames being emitted when Candle is lit.
     */
    public abstract void renderFlame(Level level, BlockPos pos, BlockState state, Vector3d particlePos);

    /**
     * Called to light the Candle at set the Soul if needed.
     * @param location The Location of the Candle being lit.
     * @param player The Player that is lighting the candle. Can be null.
     * @param lighter The Item used to light the Candle.
     */
    public static void lightCandle(Location location, Player player, ItemStack lighter) {

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
            }
        }
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
     * Used to light the Candle when a Player right clicks it.
     */
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        Location location = new Location(level, pos);
        ItemStack stack = player.getItemInHand(hand);

        //Checks if the Candle is lit. If so, attempt to trap a Soul.
        if (isLit(state)) {
            if (stack.getItem() == InitItems.LANTERN_SOUL_TRAPPED.get().asItem() || stack.getItem() == Items.SOUL_LANTERN) {
                SoulHelper.setSoulStack(stack, ((BlockEntitySoulHolder)location.getBlockEntity()).getSoul());
            }
        }

        //If not, attempt to light the Candle.
        else lightCandle(location, player, stack);

        return InteractionResult.SUCCESS;
    }

    /**
     * Used to extinguish the Candle when a Player left clicks it.
     */
    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        extinguishCandle(new Location(level, pos));
    }

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

        Location location = new Location(level, pos);

        if (isLit(state)) {
            renderFlame(level, pos, state, new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D));

            if (rand.nextFloat() < 0.17F) {
                SoundHelper.playAtLocationLocal(location, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F);
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
