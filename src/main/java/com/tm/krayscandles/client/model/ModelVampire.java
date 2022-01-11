package com.tm.krayscandles.client.model;

import com.tm.krayscandles.entity.Vampire;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class ModelVampire extends HumanoidModel<Vampire> {

    public ModelVampire(ModelPart root) {
        super(root, RenderType::entityTranslucent);
    }
}