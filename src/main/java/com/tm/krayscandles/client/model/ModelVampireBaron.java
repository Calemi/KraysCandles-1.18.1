package com.tm.krayscandles.client.model;

import com.tm.krayscandles.entity.vampire.VampireBaron;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;


public class ModelVampireBaron extends HumanoidModel<VampireBaron> {

    public ModelVampireBaron(ModelPart root) {
        super(root, RenderType::entityTranslucent);
    }
}
