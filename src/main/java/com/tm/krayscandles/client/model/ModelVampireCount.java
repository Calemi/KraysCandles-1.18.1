package com.tm.krayscandles.client.model;


import com.tm.krayscandles.entity.vampire.VampireBase;
import com.tm.krayscandles.entity.vampire.VampireCount;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class ModelVampireCount extends HumanoidModel<VampireCount> {

    public ModelVampireCount(ModelPart root) {
        super(root, RenderType::entityTranslucent);
    }
}