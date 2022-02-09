package com.tm.krayscandles.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tm.krayscandles.client.model.ModelWraith;
import com.tm.krayscandles.entity.wraith.WraithDamnedBoss;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWraithDamned extends HumanoidMobRenderer<WraithDamnedBoss, ModelWraith<WraithDamnedBoss>> {

    private static final float SCALE = 2.5F;

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/wraith.png");

    public RenderWraithDamned(EntityRendererProvider.Context context) {
        super(context, new ModelWraith<>(context.bakeLayer(ModelLayers.VEX)), 0.0F);
    }

    @Override
    protected void scale(WraithDamnedBoss entity, PoseStack poseStack, float partialTickTime) {
        poseStack.scale(SCALE, SCALE, SCALE);
    }

    @Override
    protected int getBlockLightLevel(WraithDamnedBoss entity, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(WraithDamnedBoss entity) {
        return TEXTURE;
    }
}
