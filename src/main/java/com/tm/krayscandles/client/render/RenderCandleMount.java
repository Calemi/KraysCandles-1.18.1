package com.tm.krayscandles.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.tm.calemicore.util.render.RenderedItemStack;
import com.tm.krayscandles.block.BlockCandleSoyMount;
import com.tm.krayscandles.blockentity.BlockEntityCandleSoyMount;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCandleMount implements BlockEntityRenderer<BlockEntityCandleSoyMount> {

    private final RenderedItemStack renderedItemStack = new RenderedItemStack();

    public RenderCandleMount(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(BlockEntityCandleSoyMount mount, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        renderedItemStack.setStack(mount.getCandleStack());

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.1D, 0.5D);

        Direction facing = mount.getBlockState().getValue(BlockCandleSoyMount.FACING);

        final double offset = 0.45D;
        final float rotation = 20;

        if (facing == Direction.NORTH) {
            poseStack.translate(0.0D, 0.0D, offset);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-rotation));
        }
        else if (facing == Direction.EAST) {
            poseStack.translate(-offset, 0.0D, 0.0D);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-rotation));
        }
        else if (facing == Direction.SOUTH) {
            poseStack.translate(0.0D, 0.0D, -offset);
            poseStack.mulPose(Vector3f.XP.rotationDegrees(rotation));
        }
        else if (facing == Direction.WEST) {
            poseStack.translate(offset, 0.0D, 0.0D);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(rotation));
        }

        poseStack.scale(3F, 3F, 3F);

        renderedItemStack.render(poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
