package com.tm.krayscandles.client.render.entity;

import com.tm.krayscandles.client.model.ModelWraith;
import com.tm.krayscandles.entity.wraith.WraithBase;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWraith extends HumanoidMobRenderer<WraithBase, ModelWraith<WraithBase>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/wraith.png");

    public RenderWraith(EntityRendererProvider.Context context) {
        super(context, new ModelWraith<>(context.bakeLayer(ModelLayers.VEX)), 0.3F);
    }

    @Override
    protected int getBlockLightLevel(WraithBase entity, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(WraithBase entity) {
        return TEXTURE;
    }
}
