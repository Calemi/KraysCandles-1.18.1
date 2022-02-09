package com.tm.krayscandles.client.render.entity;

import com.tm.krayscandles.client.model.ModelVampireCount;
import com.tm.krayscandles.entity.vampire.VampireCount;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderVampireCount extends HumanoidMobRenderer<VampireCount, ModelVampireCount> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/vampire.png");

    public RenderVampireCount(EntityRendererProvider.Context context) {
        super(context, new ModelVampireCount(context.bakeLayer(ModelLayers.PLAYER)), 0.3F);
        addLayer(new HumanoidArmorLayer<>(this, new ModelVampireCount(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new ModelVampireCount(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
    }

    @Override
    public ResourceLocation getTextureLocation(VampireCount entity) {
        return TEXTURE;
    }
}
