package com.tm.calemicore.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

/**
 * Used to render an Item Stack in the Level.
 */
public class RenderedItemStack {

    private ItemStack stack;

    private final float spinSpeed;
    private final float hoverSpeed;
    private final float hoverHeight;

    private long lastTime;
    private float spin;
    private float hover;

    /**
     * @param spinSpeed The spinning speed.
     * @param hoverSpeed The speed of hovering.
     * @param hoverHeight The apex of the hover height.
     */
    public RenderedItemStack(float spinSpeed, float hoverSpeed, float hoverHeight) {
        this.stack = ItemStack.EMPTY;
        this.spinSpeed = spinSpeed;
        this.hoverSpeed = hoverSpeed;
        this.hoverHeight = hoverHeight;
    }

    public RenderedItemStack() {
        this(1, 1, 1);
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    /**
     * Call this method every tick to keep item rotating and floating.
     */
    public void updateSpinningAndFloating() {

        long targetTime = 10;

        if (System.currentTimeMillis() - lastTime >= targetTime) {
            lastTime = System.currentTimeMillis();
            spin += spinSpeed;
            spin %= 360;
            hover += (0.025F * hoverSpeed);
            hover %= 2 * Math.PI;
        }
    }

    /**
     * Renders the floating item in the world. Will not render an empty stack.
     */
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, float x, float y, float z) {

        if (!stack.isEmpty()) {

            poseStack.pushPose();

            float offset = 0;
            float scale = 1;

            if (stack.getItem() instanceof BlockItem) {
                offset = -0.125F;
                scale = 1.5F;
            }

            poseStack.mulPose(Vector3f.YP.rotationDegrees(spin));

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, packedLight, packedOverlay, poseStack, buffer, 0);

            poseStack.popPose();
        }
    }
}
