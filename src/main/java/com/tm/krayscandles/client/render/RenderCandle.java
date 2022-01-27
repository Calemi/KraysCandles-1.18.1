package com.tm.krayscandles.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.tm.calemicore.util.render.RenderedItemStack;
import com.tm.krayscandles.blockentity.base.BlockEntityCandleBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCandle implements BlockEntityRenderer<BlockEntityCandleBase> {

    private final RenderedItemStack renderedItemStack1 = new RenderedItemStack();
    private final RenderedItemStack renderedItemStack2 = new RenderedItemStack();
    private final RenderedItemStack renderedItemStack3 = new RenderedItemStack();
    private final RenderedItemStack renderedItemStack4 = new RenderedItemStack();

    private long lastTime;
    private float spin;

    public RenderCandle(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(BlockEntityCandleBase candle, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if (candle.getCrystals().size() > 0) {

            setAttachedCrystal(candle, renderedItemStack1, 0);
            setAttachedCrystal(candle, renderedItemStack2, 1);
            setAttachedCrystal(candle, renderedItemStack3, 2);
            setAttachedCrystal(candle, renderedItemStack4, 3);

            long targetTime = 10;

            if (System.currentTimeMillis() - lastTime >= targetTime) {
                lastTime = System.currentTimeMillis();
                spin += 1;
                spin %= 360;
            }

            renderCrystal(renderedItemStack1, poseStack, buffer, packedLight, packedOverlay, 0.4D, 0, 0);
            if (candle.getCrystals().size() > 1) renderCrystal(renderedItemStack2, poseStack, buffer, packedLight, packedOverlay, -0.4D, 0, 0);
            if (candle.getCrystals().size() > 2) renderCrystal(renderedItemStack3, poseStack, buffer, packedLight, packedOverlay, 0, 0, 0.4D);
            if (candle.getCrystals().size() > 3) renderCrystal(renderedItemStack4, poseStack, buffer, packedLight, packedOverlay, 0, 0, -0.4D);
        }
    }

    private void setAttachedCrystal(BlockEntityCandleBase candle, RenderedItemStack renderedItemStack, int index) {

        if (candle.getCrystals().size() > index) {
            renderedItemStack.setStack(new ItemStack(candle.getCrystals().get(index).getItem()));
        }
    }

    private void renderCrystal(RenderedItemStack renderedItemStack, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, double x, double y, double z) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(spin));
        poseStack.translate(x, y, z);
        if (x != 0) poseStack.mulPose(Vector3f.YP.rotationDegrees(90));

        renderedItemStack.render(poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
