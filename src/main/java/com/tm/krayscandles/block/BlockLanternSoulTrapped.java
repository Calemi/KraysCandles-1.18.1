package com.tm.krayscandles.block;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.krayscandles.blockentity.BlockEntityLanternSoulTrapped;
import com.tm.krayscandles.init.InitBlockEntityTypes;
import com.tm.krayscandles.soul.Soul;
import com.tm.krayscandles.util.helper.CandleParticleHelper;
import com.tm.krayscandles.util.helper.SoulHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class BlockLanternSoulTrapped extends LanternBlock implements EntityBlock {

    public BlockLanternSoulTrapped() {
        super(Properties.copy(Blocks.SOUL_LANTERN));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter pLevel, List<Component> tooltip, TooltipFlag flag) {

        Soul soul = SoulHelper.getSoulStack(stack);

        if (!soul.isNull()) {
            tooltip.add(new TranslatableComponent("lore.lantern_soul_trapped").withStyle(ChatFormatting.GRAY).append(new TextComponent(" ").append(soul.getEntity().getDescription()).withStyle(ChatFormatting.LIGHT_PURPLE)));
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        CandleParticleHelper.renderFlame(level, pos, pos.getX() + 0.5D, pos.getY() + (state.getValue(HANGING) ? 0.3D : 0.25D), pos.getZ() + 0.5D);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        Soul soul = SoulHelper.getSoulStack(stack);
        Location location = new Location(level, pos);

        if (location.getBlockEntity() instanceof BlockEntityLanternSoulTrapped lantern) {
            lantern.setSoul(soul);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {

        Location location = new Location(level, pos);

        if (location.getBlockEntity() instanceof BlockEntityLanternSoulTrapped lantern) {

            ItemEntity itemEntity = ItemHelper.spawnStack(level, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, new ItemStack((this.asItem())));
            ItemStack stack = itemEntity.getItem();

            SoulHelper.setSoulStack(stack, lantern.getSoul());
            itemEntity.setItem(stack);
        }

        super.playerWillDestroy(level, pos, state, player);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return InitBlockEntityTypes.LANTERN_SOUL_TRAPPED.get().create(pos, state);
    }
}
