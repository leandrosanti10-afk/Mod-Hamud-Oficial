package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.ChabanEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ChabanModel<T extends ChabanEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(HamudMod.MOD_ID, "chaban"),
            "main"
    );

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public ChabanModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(56, 27).addBox(-3.0F, -9.0F, -5.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(56, 30).addBox(-5.0F, -7.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 34).addBox(2.0F, -8.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 23).addBox(4.0F, -8.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 0).addBox(-5.0F, -8.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 48).addBox(4.0F, -6.0F, -3.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 48).addBox(-5.0F, -6.0F, -3.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 9).addBox(-4.0F, -7.0F, 4.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 55).addBox(4.0F, -4.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(56, 21).addBox(-5.0F, -4.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 32).addBox(-2.0F, -9.0F, -4.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 39).addBox(-4.0F, -9.0F, -4.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 21).addBox(-4.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 45).addBox(-3.0F, -2.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(48, 48).addBox(2.0F, -9.0F, -4.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 37).addBox(-2.0F, -9.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 55).addBox(1.0F, -9.0F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 57).addBox(3.0F, -9.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(12, 57).addBox(4.0F, -7.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(36, 57).addBox(-5.0F, -7.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 57).addBox(-2.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(44, 57).addBox(2.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(6, 57).addBox(2.0F, -8.0F, 4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(30, 57).addBox(1.0F, -9.0F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 57).addBox(-4.0F, -8.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(11, 56).addBox(4.0F, -6.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(11, 56).addBox(-5.0F, -6.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(37, 24).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(24, 16).addBox(-4.0F, 5.0F, -3.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(22, 34).addBox(-3.0F, 6.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(22, 36).addBox(-1.0F, 5.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(30, 55).addBox(0.0F, 6.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                        .texOffs(58, 32).addBox(2.0F, 5.0F, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 5.0F, 0.0F)
        );

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4.0F, 2.0F, 0.0F)
        );

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(16, 39).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 60).addBox(-3.0F, 8.0F, -7.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 63).addBox(-3.0F, 9.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 61).addBox(-5.0F, 8.0F, -12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(1, 60).addBox(-4.0F, 7.0F, -12.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 61).addBox(-3.0F, 6.0F, -12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(6, 65).addBox(-3.0F, 7.0F, -13.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(6, 65).addBox(-4.0F, 8.0F, -13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(6, 65).addBox(-2.0F, 8.0F, -13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 61).addBox(-3.0F, 10.0F, -12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(1, 60).addBox(-2.0F, 7.0F, -12.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 61).addBox(-1.0F, 8.0F, -12.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(1, 60).addBox(-2.0F, 9.0F, -12.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(1, 60).addBox(-4.0F, 9.0F, -12.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 63).addBox(-4.0F, 8.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 63).addBox(-3.0F, 7.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(4, 63).addBox(-2.0F, 8.0F, -7.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, 2.0F, 0.0F)
        );

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 39).addBox(-2.0F, 10.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 12.0F, 0.0F)
        );

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(24, 23).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 15).addBox(-2.0F, 10.0F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-2.0F, 12.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(
            T entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        this.right_leg.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.4F * limbSwingAmount;

        this.right_arm.xRot = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.2F * limbSwingAmount;
        this.left_arm.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

        this.body.yRot = (float) Math.sin(limbSwing * 0.3331F) * 0.08F * limbSwingAmount;

        if (limbSwingAmount < 0.05F) {
            this.right_arm.xRot = 0.0F;
            this.left_arm.xRot = 0.0F;
            this.right_leg.xRot = 0.0F;
            this.left_leg.xRot = 0.0F;
            this.body.yRot = 0.0F;
        }
    }

    @Override
    public void renderToBuffer(
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            int packedLight,
            int packedOverlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
