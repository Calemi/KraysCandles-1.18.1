package com.tm.krayscandles.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.tm.krayscandles.entity.wraith.Wraith;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelWraith<T extends Monster> extends HumanoidModel<T> {

    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public ModelWraith(ModelPart root) {
        super(root);
        this.leftLeg.visible = false;
        this.hat.visible = false;
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -2.0F, 6.0F, 10.0F, 4.0F), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 32).addBox(-20.0F, 0.0F, 0.0F, 20.0F, 12.0F, 1.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 20.0F, 12.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.rightWing, this.leftWing));
    }

    /**
     * Sets this entity's model rotation angles
     */
    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.rightLeg.xRot += ((float)Math.PI / 5F);
        this.rightWing.z = 2.0F;
        this.leftWing.z = 2.0F;
        this.rightWing.y = 1.0F;
        this.leftWing.y = 1.0F;
        this.rightWing.yRot = 0.47123894F + Mth.cos(ageInTicks * 45.836624F * ((float)Math.PI / 180F)) * (float)Math.PI * 0.05F;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.leftWing.zRot = -0.47123894F;
        this.leftWing.xRot = 0.47123894F;
        this.rightWing.xRot = 0.47123894F;
        this.rightWing.zRot = 0.47123894F;
    }
}
