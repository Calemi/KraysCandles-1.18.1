package com.tm.krayscandles.item.base;

import com.tm.krayscandles.main.KraysCandles;
import com.tm.krayscandles.ritual.IRitualItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * The base class for Wands.
 */
public abstract class ItemWandBase extends ItemBase implements IRitualItem {

    public ItemWandBase() {
        super(new Item.Properties().tab(KraysCandles.TAB_TOOL).stacksTo(1));
    }

    /**
     * @return The cooldown in seconds after a successful cast.
     */
    public abstract int getCooldown();

    /**
     * Called when a Wand is right-clicked. Return true if something happened (starts cooldown).
     */
    public abstract boolean castWand(Level level, Player player);

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);

        if (castWand(level, player)) {
            player.getCooldowns().addCooldown(this, getCooldown() * 20);
            player.playSound(SoundEvents.CONDUIT_AMBIENT, 1, 10);
            return InteractionResultHolder.success(heldStack);
        }

        else return InteractionResultHolder.fail(heldStack);
    }
}
