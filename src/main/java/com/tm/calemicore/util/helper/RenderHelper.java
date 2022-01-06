package com.tm.calemicore.util.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tm.calemicore.util.FloatingItemStack;
import net.minecraft.client.renderer.MultiBufferSource;

/**
 * Use this class for general rendering methods.
 */
public class RenderHelper {

    /**
     * Renders a floating Item.
     * @param floatingStack The stack to be rendered.
     * @param x The x position.
     * @param y The y position.
     * @param z The z position.
     */
    public static void renderFloatingItem(FloatingItemStack floatingStack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, float x, float y, float z) {
        floatingStack.update();
        floatingStack.render(poseStack, buffer, packedLight, packedOverlay, x, y, z);
    }
}
