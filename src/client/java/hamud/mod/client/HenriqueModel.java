package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.HenriqueEntity;
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

public class HenriqueModel<T extends HenriqueEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(HamudMod.MOD_ID, "henrique"),
			"main"
	);

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public HenriqueModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild(
				"head",
				CubeListBuilder.create()
						.texOffs(0, 11)
						.addBox(
								-4.0F,
								-8.0F,
								-4.0F,
								8.0F,
								8.0F,
								8.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 27)
						.addBox(
								-4.0F,
								-10.0F,
								-4.0F,
								8.0F,
								4.0F,
								8.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(0, 0)
						.addBox(
								-5.0F,
								-7.0F,
								-5.0F,
								10.0F,
								1.0F,
								10.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		PartDefinition body = partdefinition.addOrReplaceChild(
				"body",
				CubeListBuilder.create()
						.texOffs(32, 11)
						.addBox(
								-4.0F,
								-3.0F,
								-2.0F,
								8.0F,
								12.0F,
								4.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(0.0F, 3.0F, 0.0F)
		);

		PartDefinition right_arm = partdefinition.addOrReplaceChild(
				"right_arm",
				CubeListBuilder.create()
						.texOffs(32, 43)
						.addBox(
								-4.0F,
								-2.0F,
								-2.0F,
								4.0F,
								12.0F,
								4.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(40, 0)
						.addBox(
								-3.0F,
								8.0F,
								-9.0F,
								2.0F,
								2.0F,
								7.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(-4.0F, 2.0F, 0.0F)
		);

		PartDefinition left_arm = partdefinition.addOrReplaceChild(
				"left_arm",
				CubeListBuilder.create()
						.texOffs(16, 39)
						.addBox(
								0.0F,
								-2.0F,
								-2.0F,
								4.0F,
								12.0F,
								4.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(4.0F, 2.0F, 0.0F)
		);

		PartDefinition right_leg = partdefinition.addOrReplaceChild(
				"right_leg",
				CubeListBuilder.create()
						.texOffs(32, 27)
						.addBox(
								-2.0F,
								0.0F,
								-2.0F,
								4.0F,
								12.0F,
								4.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(50, 42)
						.addBox(
								-2.0F,
								10.0F,
								-4.0F,
								4.0F,
								2.0F,
								2.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(50, 52)
						.addBox(
								-3.0F,
								0.0F,
								-3.0F,
								1.0F,
								6.0F,
								6.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(1, 0)
						.addBox(
								-3.0F,
								1.0F,
								-1.0F,
								1.0F,
								2.0F,
								2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(-2.0F, 12.0F, 0.0F)
		);

		PartDefinition left_leg = partdefinition.addOrReplaceChild(
				"left_leg",
				CubeListBuilder.create()
						.texOffs(0, 39)
						.addBox(
								-2.0F,
								0.0F,
								-2.0F,
								4.0F,
								12.0F,
								4.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(48, 27)
						.addBox(
								2.0F,
								0.0F,
								-3.0F,
								2.0F,
								4.0F,
								4.0F,
								new CubeDeformation(0.0F)
						)
						.texOffs(50, 42)
						.addBox(
								-2.0F,
								10.0F,
								-4.0F,
								4.0F,
								2.0F,
								2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offset(2.0F, 12.0F, 0.0F)
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
		this.head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}