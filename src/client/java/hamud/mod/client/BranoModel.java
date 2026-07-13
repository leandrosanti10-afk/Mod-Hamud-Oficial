package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.BranoEntity;
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

public class BranoModel<T extends BranoEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(HamudMod.MOD_ID, "brano"),
			"main"
	);

	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart glass;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public BranoModel(ModelPart root) {
		this.head = root.getChild("head");
		this.hair = this.head.getChild("hair");
		this.glass = this.head.getChild("glass");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r1", CubeListBuilder.create()
						.texOffs(40, 59).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -30.0F, 8.0F, 0.5236F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r2", CubeListBuilder.create()
						.texOffs(32, 59).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -30.0F, 8.0F, 0.5236F, -0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r3", CubeListBuilder.create()
						.texOffs(56, 59).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -30.0F, 8.0F, 0.5236F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r4", CubeListBuilder.create()
						.texOffs(32, 44).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -26.0F, 7.0F, 0.3054F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r5", CubeListBuilder.create()
						.texOffs(48, 61).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -26.0F, 5.0F, 0.3054F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r6", CubeListBuilder.create()
						.texOffs(40, 24).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -26.0F, 7.0F, 0.3054F, 0.0F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r7", CubeListBuilder.create()
						.texOffs(16, 62).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -26.0F, 5.0F, 0.3054F, 0.0F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r8", CubeListBuilder.create()
						.texOffs(40, 44).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -26.0F, 7.0F, 0.3054F, -0.1745F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r9", CubeListBuilder.create()
						.texOffs(60, 28).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -26.0F, 5.0F, 0.3054F, -0.1745F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r10", CubeListBuilder.create()
						.texOffs(44, 32).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -26.0F, 7.0F, 0.3054F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r11", CubeListBuilder.create()
						.texOffs(8, 62).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -26.0F, 5.0F, 0.3054F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r12", CubeListBuilder.create()
						.texOffs(0, 48).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -26.0F, 6.0F, 0.3054F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r13", CubeListBuilder.create()
						.texOffs(60, 14).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -26.0F, 5.0F, 0.3054F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r14", CubeListBuilder.create()
						.texOffs(56, 52).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -30.0F, 8.0F, 0.5236F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r15", CubeListBuilder.create()
						.texOffs(24, 55).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -31.0F, 3.0F, -0.7418F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r16", CubeListBuilder.create()
						.texOffs(40, 52).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -31.0F, 1.0F, -0.7418F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r17", CubeListBuilder.create()
						.texOffs(24, 48).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -31.0F, -1.0F, -0.7418F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r18", CubeListBuilder.create()
						.texOffs(16, 48).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -31.0F, -3.0F, -0.7418F, -0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r19", CubeListBuilder.create()
						.texOffs(16, 55).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -31.0F, 3.0F, -0.7418F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r20", CubeListBuilder.create()
						.texOffs(52, 14).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -31.0F, 1.0F, -0.7418F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r21", CubeListBuilder.create()
						.texOffs(48, 47).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -31.0F, -1.0F, -0.7418F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r22", CubeListBuilder.create()
						.texOffs(56, 45).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -28.0F, 0.0F, -1.3963F, -0.3491F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r23", CubeListBuilder.create()
						.texOffs(56, 7).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -28.0F, 0.0F, -1.3963F, 0.3491F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r24", CubeListBuilder.create()
						.texOffs(56, 38).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -30.0F, 0.0F, -1.3963F, -0.2618F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r25", CubeListBuilder.create()
						.texOffs(56, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -30.0F, 0.0F, -1.3963F, 0.2618F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r26", CubeListBuilder.create()
						.texOffs(56, 21).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -30.0F, -3.0F, -1.3963F, -0.2618F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r27", CubeListBuilder.create()
						.texOffs(0, 56).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -30.0F, -3.0F, -1.3963F, 0.2618F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r28", CubeListBuilder.create()
						.texOffs(32, 52).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -31.0F, 1.0F, -0.7418F, -0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r29", CubeListBuilder.create()
						.texOffs(48, 7).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -31.0F, -3.0F, -0.7418F, 0.3927F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r30", CubeListBuilder.create()
						.texOffs(48, 54).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -31.0F, 3.0F, -0.7418F, -0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r31", CubeListBuilder.create()
						.texOffs(48, 24).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -31.0F, -1.0F, -0.7418F, -0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r32", CubeListBuilder.create()
						.texOffs(8, 48).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -31.0F, -3.0F, -0.7418F, -0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r33", CubeListBuilder.create()
						.texOffs(8, 55).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -31.0F, 3.0F, -0.7418F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r34", CubeListBuilder.create()
						.texOffs(52, 31).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -31.0F, 1.0F, -0.7418F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r35", CubeListBuilder.create()
						.texOffs(48, 40).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -31.0F, -1.0F, -0.7418F, 0.2182F, 0.0F)
		);

		hair.addOrReplaceChild("cube_r36", CubeListBuilder.create()
						.texOffs(48, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -31.0F, -3.0F, -0.7418F, 0.2182F, 0.0F)
		);

		head.addOrReplaceChild("glass", CubeListBuilder.create()
						.texOffs(60, 35).addBox(-3.0F, -28.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(32, 32).addBox(-5.0F, -30.0F, -5.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
						.texOffs(32, 38).addBox(4.0F, -30.0F, -5.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
						.texOffs(24, 62).addBox(1.0F, -28.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(44, 40).addBox(3.0F, -30.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(52, 21).addBox(0.0F, -30.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(56, 28).addBox(-1.0F, -30.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(64, 2).addBox(-4.0F, -30.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 63).addBox(1.0F, -31.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(64, 0).addBox(-3.0F, -31.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(1, 1).addBox(-3.0F, -30.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(1, 1).addBox(1.0F, -30.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(0, 16).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 4.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(16, 32).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 2.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
						.texOffs(32, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 2.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(40, 20).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(40, 16).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 12.0F, 0.0F)
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
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.zRot = 0.0F;

		this.body.xRot = 0.0F;
		this.body.yRot = 0.0F;
		this.body.zRot = 0.0F;

		this.right_arm.xRot = 0.0F;
		this.right_arm.yRot = 0.0F;
		this.right_arm.zRot = 0.0F;

		this.left_arm.xRot = 0.0F;
		this.left_arm.yRot = 0.0F;
		this.left_arm.zRot = 0.0F;

		this.right_leg.xRot = 0.0F;
		this.right_leg.yRot = 0.0F;
		this.right_leg.zRot = 0.0F;

		this.left_leg.xRot = 0.0F;
		this.left_leg.yRot = 0.0F;
		this.left_leg.zRot = 0.0F;

		float walkSpeed = limbSwing * 0.6662F;
		float walkAmount = Math.min(limbSwingAmount, 1.0F);

		this.right_leg.xRot = (float) Math.cos(walkSpeed) * 1.4F * walkAmount;
		this.left_leg.xRot = (float) Math.cos(walkSpeed + Math.PI) * 1.4F * walkAmount;

		this.right_arm.xRot = (float) Math.cos(walkSpeed + Math.PI) * 1.2F * walkAmount;
		this.left_arm.xRot = (float) Math.cos(walkSpeed) * 1.2F * walkAmount;

		this.body.yRot = (float) Math.sin(limbSwing * 0.3331F) * 0.08F * walkAmount;

		if (walkAmount < 0.05F) {
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
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}