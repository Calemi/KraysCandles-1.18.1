package com.tm.krayscandles.init;

import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.client.render.*;
import com.tm.krayscandles.main.DispenserLightBehavior;
import com.tm.krayscandles.main.KCReference;
import com.tm.krayscandles.ritual.RitualRecipes;
import com.tm.krayscandles.ritual.RitualStructureTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Handles setting up the mod on the client and common side.
 */
public class InitSetup {

    /**
     * Used to register everything the mod needs on the client and server side.
     */
    public static void initCommon(final FMLCommonSetupEvent event) {
        LogHelper.log(KCReference.MOD_NAME, "Initializing Common-Side for " + KCReference.MOD_NAME);

        InitEvents.initCommon();

        DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new DispenserLightBehavior());
        DispenserBlock.registerBehavior(Items.LANTERN, new DispenserLightBehavior());
        DispenserBlock.registerBehavior(Items.SOUL_LANTERN, new DispenserLightBehavior());
        DispenserBlock.registerBehavior(InitItems.LANTERN_SOUL_TRAPPED.get().asItem(), new DispenserLightBehavior());

        RitualStructureTypes.init();
        RitualRecipes.init();
    }

    /**
     * Used to register everything the mod needs on the client side only.
     */
    public static void initClient(final FMLClientSetupEvent event) {
        LogHelper.log(KCReference.MOD_NAME, "Initializing Client-Side for " + KCReference.MOD_NAME);

        InitEvents.initClient();
        InitRenderLayers.init();

        BlockEntityRenderers.register(InitBlockEntityTypes.STONE_ALTAR_TILE.get(), RenderStoneAltarTile::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_SOY_MOUNT.get(), RenderCandleMount::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_BLESSED.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_CURSED.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_PURGED.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_FIRE.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_LEVITATE.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_ZEN.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_INVIS.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_LUCK.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_ENERGY.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_CAVERN.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_NULL.get(), RenderCandle::new);
        BlockEntityRenderers.register(InitBlockEntityTypes.CANDLE_CURSED.get(), RenderCandle::new);


        ItemProperties.register(InitItems.CANDLE_SOY_COLORED_ITEM.get(), new ResourceLocation("color"), (stack, level, player, damage) -> stack.getDamageValue());

        EntityRenderers.register(InitEntityTypes.WRAITH_FIRE.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_WATER.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_AIR.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_EXPLOSION.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_MAGIC.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_MOB.get(), RenderWraith::new);
        EntityRenderers.register(InitEntityTypes.WRAITH_DAMNED.get(), RenderWraithDamned::new);
        EntityRenderers.register(InitEntityTypes.VAMPIRE.get(), RenderVampire::new);
        EntityRenderers.register(InitEntityTypes.VAMPIRE_BARON.get(), RenderVampireBaron::new);
        EntityRenderers.register(InitEntityTypes.VAMPIRE_BARONESS.get(), RenderVampireBaroness::new);
        EntityRenderers.register(InitEntityTypes.CLOUD.get(), NoopRenderer::new);
    }
}


