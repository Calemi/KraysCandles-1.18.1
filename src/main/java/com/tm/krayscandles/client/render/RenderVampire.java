package com.tm.krayscandles.client.render;

import com.tm.krayscandles.client.model.ModelVampire;
import com.tm.krayscandles.entity.vampire.Vampire;
import com.tm.krayscandles.main.KCReference;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderVampire extends HumanoidMobRenderer<Vampire, ModelVampire> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(KCReference.MOD_ID, "textures/entity/vampire.png");

    public RenderVampire(EntityRendererProvider.Context context) {
        super(context, new ModelVampire(context.bakeLayer(ModelLayers.PLAYER)), 0.3F);
        addLayer(new HumanoidArmorLayer<>(this, new ModelVampire(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new ModelVampire(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
    }

    @Override
    public ResourceLocation getTextureLocation(Vampire entity) {
        return TEXTURE;
    }
}
