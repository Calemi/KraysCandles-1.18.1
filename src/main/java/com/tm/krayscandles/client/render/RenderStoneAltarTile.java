package com.tm.krayscandles.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tm.calemicore.util.RenderedItemStack;
import com.tm.calemicore.util.helper.RenderHelper;
import com.tm.krayscandles.blockentity.BlockEntityStoneAltarTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderStoneAltarTile implements BlockEntityRenderer<BlockEntityStoneAltarTile> {

    private final RenderedItemStack renderedItemStack = new RenderedItemStack();

    public RenderStoneAltarTile(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(BlockEntityStoneAltarTile blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        renderedItemStack.setStack(blockEntity.getRitualStack());
        RenderHelper.renderBobbingItem(renderedItemStack, poseStack, buffer, packedLight, packedOverlay, 0, 0, 0);
    }
}
