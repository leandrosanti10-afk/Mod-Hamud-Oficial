package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.VelociraptorEntity;
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

public class VelociraptorModel<T extends VelociraptorEntity> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(HamudMod.MOD_ID, "velociraptor"),
            "main"
    );

    private final ModelPart neck;
    private final ModelPart jaw;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tail_mid;
    private final ModelPart tail_tip;
    private final ModelPart tail_end;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public VelociraptorModel(ModelPart root) {
        this.neck = root.getChild("neck");
        this.jaw = this.neck.getChild("jaw");
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.tail_mid = this.tail.getChild("tail_mid");
        this.tail_tip = this.tail.getChild("tail_tip");
        this.tail_end = this.tail.getChild("tail_end");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition neck = partdefinition.addOrReplaceChild(
                "neck",
                CubeListBuilder.create()
                        .texOffs(28, 0).addBox(-2.0F, -6.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(26, 15).addBox(-2.0F, -9.0F, -6.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(24, 42).addBox(-1.0F, -9.0F, -9.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 15.0F, -6.0F)
        );

        neck.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create()
                        .texOffs(-1, 46).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -5.0F, -4.0F, 0.0F, 3.1416F, 0.0F)
        );

        neck.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create()
                        .texOffs(-1, 46).addBox(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -5.0F, -4.0F, 0.0F, 3.1416F, 0.0F)
        );

        neck.addOrReplaceChild(
                "cube_r3",
                CubeListBuilder.create()
                        .texOffs(3, 52).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -5.0F, -10.0F, 0.0F, 3.1416F, 0.0F)
        );

        neck.addOrReplaceChild(
                "jaw",
                CubeListBuilder.create()
                        .texOffs(22, 35).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -5.0F, -3.0F)
        );

        partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 26).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 15.0F, -2.0F)
        );

        PartDefinition tail = partdefinition.addOrReplaceChild(
                "tail",
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        tail.addOrReplaceChild(
                "tail_mid",
                CubeListBuilder.create()
                        .texOffs(0, 15).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -10.0F, 5.0F)
        );

        tail.addOrReplaceChild(
                "tail_tip",
                CubeListBuilder.create()
                        .texOffs(22, 26).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -10.0F, 15.0F)
        );

        tail.addOrReplaceChild(
                "tail_end",
                CubeListBuilder.create()
                        .texOffs(0, 37).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -11.0F, 23.0F)
        );

        PartDefinition right_arm = partdefinition.addOrReplaceChild(
                "right_arm",
                CubeListBuilder.create(),
                PartPose.offset(-3.0F, 17.0F, -5.0F)
        );

        right_arm.addOrReplaceChild(
                "cube_r4",
                CubeListBuilder.create()
                        .texOffs(44, 2).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 4.0F, 0.0F, 0.6545F, 0.0F, 0.0F)
        );

        right_arm.addOrReplaceChild(
                "cube_r5",
                CubeListBuilder.create()
                        .texOffs(0, 44).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 3.0F, 0.0F, 0.6545F, 0.0F, 0.0F)
        );

        right_arm.addOrReplaceChild(
                "cube_r6",
                CubeListBuilder.create()
                        .texOffs(42, 43).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 2.0F, 1.0F, 0.3927F, 0.0F, 0.0F)
        );

        PartDefinition left_arm = partdefinition.addOrReplaceChild(
                "left_arm",
                CubeListBuilder.create(),
                PartPose.offset(3.0F, 17.0F, -5.0F)
        );

        left_arm.addOrReplaceChild(
                "cube_r7",
                CubeListBuilder.create()
                        .texOffs(42, 32).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.6545F, 0.0F, 0.0F)
        );

        left_arm.addOrReplaceChild(
                "cube_r8",
                CubeListBuilder.create()
                        .texOffs(44, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.6545F, 0.0F, 0.0F)
        );

        left_arm.addOrReplaceChild(
                "cube_r9",
                CubeListBuilder.create()
                        .texOffs(14, 37).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.3927F, 0.0F, 0.0F)
        );

        partdefinition.addOrReplaceChild(
                "left_leg",
                CubeListBuilder.create()
                        .texOffs(38, 35).addBox(0.0F, -2.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(42, 25).addBox(0.0F, 3.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(28, 12).addBox(0.0F, 7.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(2.0F, 16.0F, 2.0F)
        );

        partdefinition.addOrReplaceChild(
                "right_leg",
                CubeListBuilder.create()
                        .texOffs(14, 42).addBox(-2.0F, -2.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(34, 43).addBox(-2.0F, 3.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                        .texOffs(36, 12).addBox(-2.0F, 7.0F, -3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-2.0F, 16.0F, 2.0F)
        );

        return LayerDefinition.create(meshdefinition, 64, 64);
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
        // Limpa rotações para não acumular animação
        this.neck.xRot = 0.0F;
        this.neck.yRot = 0.0F;
        this.neck.zRot = 0.0F;

        this.jaw.xRot = 0.0F;
        this.jaw.yRot = 0.0F;
        this.jaw.zRot = 0.0F;

        this.body.yRot = 0.0F;

        this.tail.yRot = 0.0F;
        this.tail_mid.yRot = 0.0F;
        this.tail_tip.yRot = 0.0F;
        this.tail_end.yRot = 0.0F;

        this.right_arm.xRot = 0.0F;
        this.left_arm.xRot = 0.0F;

        this.right_leg.xRot = 0.0F;
        this.left_leg.xRot = 0.0F;

        float walkSpeed = limbSwing * 0.6662F;
        float walkAmount = Math.min(limbSwingAmount, 1.0F);

        // =========================
        // PESCOÇO + CABEÇA JUNTOS
        // =========================
        this.neck.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.25F;
        this.neck.xRot = headPitch * ((float) Math.PI / 180F) * 0.15F;

        float neckBob = (float) Math.sin(ageInTicks * 0.10F) * 0.07F;
        this.neck.xRot += neckBob;

        // =========================
        // MANDÍBULA
        // =========================
        // Jaw está dentro do neck, então acompanha cabeça/pescoço.
        // Só abre e fecha verticalmente.
        float jawOpen = 0.16F + (float) Math.sin(ageInTicks * 0.20F) * 0.14F;

        if (jawOpen < 0.03F) {
            jawOpen = 0.03F;
        }

        this.jaw.xRot = jawOpen;

        // =========================
        // PERNAS
        // =========================
        this.right_leg.xRot = (float) Math.cos(walkSpeed) * 1.25F * walkAmount;
        this.left_leg.xRot = (float) Math.cos(walkSpeed + Math.PI) * 1.25F * walkAmount;

        // =========================
        // BRAÇOS
        // =========================
        this.right_arm.xRot = 0.12F + (float) Math.cos(walkSpeed + Math.PI) * 0.14F * walkAmount;
        this.left_arm.xRot = 0.12F + (float) Math.cos(walkSpeed) * 0.14F * walkAmount;

        // =========================
        // CORPO
        // =========================
        this.body.yRot = (float) Math.sin(walkSpeed) * 0.035F * walkAmount;

        // =========================
        // CAUDA
        // =========================
        float idleTailWave = (float) Math.sin(ageInTicks * 0.07F) * 0.18F;
        float walkingTailWave = (float) Math.sin(walkSpeed) * 0.08F * walkAmount;

        float tailWave = idleTailWave + walkingTailWave;

        this.tail.yRot = tailWave;
        this.tail_mid.yRot = tailWave * 0.75F;
        this.tail_tip.yRot = tailWave * 0.55F;
        this.tail_end.yRot = tailWave * 0.35F;

        // =========================
        // PARADO
        // =========================
        if (walkAmount < 0.05F) {
            this.right_leg.xRot = 0.0F;
            this.left_leg.xRot = 0.0F;

            this.right_arm.xRot = 0.12F;
            this.left_arm.xRot = 0.12F;

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
        neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}