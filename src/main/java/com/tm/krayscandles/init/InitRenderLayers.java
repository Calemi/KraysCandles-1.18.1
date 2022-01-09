package com.tm.krayscandles.init;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

/**
 * Handles setting up render layers for Blocks.
 */
public class InitRenderLayers {

    /**
     * Called to initialize the render layers.
     */
    public static void init() {

        ItemBlockRenderTypes.setRenderLayer(InitItems.SOYBEAN_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(InitItems.CANDLE_BLESSED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(InitItems.STATUE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(InitItems.CANDLE_WAX_BEE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(InitItems.CANDLE_INVIS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(InitItems.CANDLE_SOY_MOUNT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(InitItems.LANTERN_SOUL_TRAPPED.get(), RenderType.cutout());
    }
}