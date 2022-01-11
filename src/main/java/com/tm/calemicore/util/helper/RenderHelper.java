package com.tm.calemicore.util.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tm.calemicore.util.RenderedItemStack;
import net.minecraft.client.renderer.MultiBufferSource;

/**
 * Use this class for general rendering methods.
 */
public class RenderHelper {

    /**
     * Renders a spinning and floating Item Stack in the Level.
     * @param renderedItemStack The stack to be rendered.
     * @param x The x position.
     * @param y The y position.
     * @param z The z position.
     */
    public static void renderBobbingItem(RenderedItemStack renderedItemStack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, float x, float y, float z) {
        renderedItemStack.updateSpinningAndFloating();
        renderItem(renderedItemStack, poseStack, buffer, packedLight, packedOverlay, x, y, z);
    }

    /**
     * Renders a spinning and floating Item Stack in the Level.
     * @param renderedItemStack The stack to be rendered.
     * @param x The x position.
     * @param y The y position.
     * @param z The z position.
     */
    public static void renderItem(RenderedItemStack renderedItemStack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, float x, float y, float z) {
        renderedItemStack.render(poseStack, buffer, packedLight, packedOverlay, x, y, z);
    }
}
