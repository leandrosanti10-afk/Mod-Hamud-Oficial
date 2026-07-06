package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.RemyEntity;
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

public class RemyModel<T extends RemyEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(HamudMod.MOD_ID, "remy"),
			"main"
	);

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public RemyModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

		public static LayerDefinition createBodyLayer () {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 25).addBox(-4.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -7.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 16).addBox(-9.0F, -3.0F, -1.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-15.0F, 0.0F, -1.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, -7.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 59).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(56, 44).addBox(0.0F, -4.0F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 29).addBox(-7.0F, -8.0F, -1.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -3.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -1.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -1.0F, -1.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 6.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(32, 29).addBox(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 6.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-4.0F, 2.0F, -1.0F));

		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 50).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, -14.0F, 1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r9 = right_arm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(34, 51).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(34, 51).addBox(-6.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(34, 51).addBox(-3.0F, -1.0F, -1.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -15.0F, 2.8362F, 0.0F, 0.0F));

		PartDefinition cube_r10 = right_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(56, 25).addBox(0.0F, -18.0F, -1.0F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 9.0F, 3.0F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r11 = right_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(52, 49).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r12 = right_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(16, 45).addBox(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 10.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(4.0F, 2.0F, -1.0F));

		PartDefinition cube_r13 = left_arm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 49).addBox(-5.0F, -1.0F, -1.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r14 = left_arm.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(16, 45).addBox(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 10.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, -1.0F));

		PartDefinition cube_r15 = right_leg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(52, 55).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r16 = right_leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 45).addBox(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, -1.0F));

		PartDefinition cube_r17 = left_leg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(52, 55).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r18 = left_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 45).addBox(-3.0F, -12.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

		@Override
		public void setupAnim (
			T entity,
		float limbSwing,
		float limbSwingAmount,
		float ageInTicks,
		float netHeadYaw,
		float headPitch
){
		// Cabeça olha para onde o mob olha
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		// Caminhada estilo player
		this.right_leg.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.xRot = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.4F * limbSwingAmount;

		this.right_arm.xRot = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.2F * limbSwingAmount;
		this.left_arm.xRot = (float) Math.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

		// Pequeno balanço do corpo andando
		this.body.yRot = (float) Math.sin(limbSwing * 0.3331F) * 0.08F * limbSwingAmount;

		// Se estiver parado, zera o balanço
		if (limbSwingAmount < 0.05F) {
			this.right_arm.xRot = 0.0F;
			this.left_arm.xRot = 0.0F;
			this.right_leg.xRot = 0.0F;
			this.left_leg.xRot = 0.0F;
			this.body.yRot = 0.0F;
		}
	}

		@Override
		public void renderToBuffer (
			PoseStack poseStack,
			VertexConsumer vertexConsumer,
		int packedLight,
		int packedOverlay,
		float red,
		float green,
		float blue,
		float alpha
){
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	}