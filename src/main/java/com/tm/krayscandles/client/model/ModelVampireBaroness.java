package com.tm.krayscandles.client.model;

import com.tm.krayscandles.entity.vampire.VampireBaroness;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class ModelVampireBaroness extends HumanoidModel<VampireBaroness> {

    public ModelVampireBaroness(ModelPart root) {
        super(root, RenderType::entityTranslucent);
    }
}
