package com.tm.krayscandles.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.tm.calemicore.util.helper.LogHelper;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderStoneAltarTile implements BlockEntityRenderer<BlockEntityStoneAltarTile> {

    private long lastTime;
    private float rot;
    private float hover;

    public RenderStoneAltarTile(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(BlockEntityStoneAltarTile blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if (!blockEntity.getRitualStack().isEmpty()) {

            long targetTime = 10;
            if (System.currentTimeMillis() - lastTime >= targetTime) {
                lastTime = System.currentTimeMillis();
                rot += 1F;
                rot %= 360;
                hover += 0.025F;
                hover %= 2 * Math.PI;
            }

            poseStack.pushPose();

            float offset = 0;
            float scale = 1;

            if (blockEntity.getRitualStack().getItem() instanceof BlockItem) {
                offset = -0.125F;
                scale = 1.5F;
            }

            poseStack.translate(0.5D, 0.5D + offset + (Mth.sin(hover) / 5), 0.5D);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(rot));
            poseStack.scale(scale, scale, scale);

            LogHelper.logCommon(KCReference.MOD_NAME, blockEntity.getLevel(), "Render");

            Minecraft.getInstance().getItemRenderer().renderStatic(blockEntity.getRitualStack(), ItemTransforms.TransformType.GROUND, 0, 0, poseStack, buffer, 0);

            poseStack.popPose();
        }
    }
}
