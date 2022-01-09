package com.tm.krayscandles.events;

import com.tm.calemicore.util.Location;
import com.tm.calemicore.util.helper.ItemHelper;
import com.tm.calemicore.util.helper.LevelEffectHelper;
import com.tm.calemicore.util.helper.SoundHelper;
import com.tm.krayscandles.ritual.RitualRecipe;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.ritual.RitualStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RitualEvent {

    @SubscribeEvent
    public void onRitual(PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getPlayer();

        Level level = event.getWorld();
        BlockPos pos = event.getPos();
        Location location = new Location(level, pos);

        if (event.getHand() == InteractionHand.MAIN_HAND) {

            for (RitualRecipe recipe : RitualRecipes.allRitualRecipes) {

                boolean handAir = recipe.getHandItem().equals(Items.AIR);

                if (handAir || recipe.getHandItem().equals(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {

                    if (recipe.isRitualComplete(level, pos, player)) {

                        if (recipe.getBlockResult() != null) {
                            location.setBlock(recipe.getBlockResult());
                        }

                        if (recipe.getDropResult() != null) {
                            ItemHelper.spawnStackAtEntity(level, player, new ItemStack(recipe.getDropResult()));
                        }

                        if (recipe.getRitualStructure() == RitualStructureTypes.CANDLE) {
                            LevelEffectHelper.spawnLightning(location, true);
                        }

                        /*else if (recipe == RitualRecipes.WRAITH) {
                            world.addEntity(new EntityWraithDamned(world, pos));
                        }*/

                        if (!handAir) player.getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                        player.swing(InteractionHand.MAIN_HAND);

                        SoundHelper.playAtLocation(location, recipe.getRitualStructure().getSound(), SoundSource.BLOCKS, 1, 1);

                        return;
                    }
                }
            }
        }
    }
}















