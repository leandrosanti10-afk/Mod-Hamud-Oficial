package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hamud.mod.HamudMod;
import hamud.mod.entity.BorgesEntity;
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

public class BorgesModel<T extends BorgesEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(HamudMod.MOD_ID, "borges"),
			"main"
	);

	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public BorgesModel(ModelPart root) {
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

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(46, 43).addBox(-3.0F, -4.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(48, 12).addBox(-7.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(46, 37).addBox(-5.0F, -2.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(46, 35).addBox(-3.0F, -7.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(4, 48).addBox(1.0F, -4.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(48, 4).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(48, 6).addBox(3.0F, -6.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(48, 9).addBox(-4.0F, -6.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(0, 48).addBox(-6.0F, -10.0F, -5.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(10, 48).addBox(-5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(8, 4).addBox(1.0F, -8.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(3, 4).addBox(-4.0F, -8.0F, -5.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(15, 54).addBox(-3.0F, -6.0F, -5.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
						.texOffs(0, 16).addBox(-4.0F, -3.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(46, 39).addBox(1.0F, -3.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(48, 0).addBox(-3.0F, -3.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(32, 32).addBox(0.0F, -1.0F, 2.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(40, 16).addBox(-4.0F, -1.0F, 2.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 3.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(32, 0).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 2.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
						.texOffs(16, 32).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 2.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(46, 29).addBox(-2.0F, 11.0F, -7.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F)
		);

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(32, 45).addBox(-2.0F, 11.0F, -7.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
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
		this.head.xRot = 0.0F;
		this.head.yRot = 0.0F;
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

		if (entity.isInWater()) {
			/*
			 * Padrão de nado:
			 * 1 - braço estica para frente
			 * 2 - braço puxa para trás na linha horizontal do tronco
			 * 3 - braço desce ao lado do corpo
			 * 4 - braço retorna para frente
			 */

			float swim = ageInTicks * 0.24F;

			/*
			 * Cabeça mais levantada.
			 */
			this.head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.15F;
			this.head.xRot = 0.06F + (float) Math.sin(swim) * 0.025F;

			float cycle = (swim % ((float) Math.PI * 2.0F)) / ((float) Math.PI * 2.0F);

			animateSwimmingArmRight(cycle);

			float leftCycle = (cycle + 0.5F) % 1.0F;
			animateSwimmingArmLeft(leftCycle);

			/*
			 * Pernas batendo alternadas e suaves.
			 */
			this.right_leg.xRot = 0.18F + (float) Math.sin(swim + Math.PI) * 0.32F;
			this.left_leg.xRot = 0.18F + (float) Math.sin(swim) * 0.32F;

			return;
		}

		/*
		 * Fora da água: caminhada normal.
		 */
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.right_leg.xRot = (float) Math.cos(walkSpeed) * 1.4F * walkAmount;
		this.left_leg.xRot = (float) Math.cos(walkSpeed + Math.PI) * 1.4F * walkAmount;

		this.right_arm.xRot = (float) Math.cos(walkSpeed + Math.PI) * 1.2F * walkAmount;
		this.left_arm.xRot = (float) Math.cos(walkSpeed) * 1.2F * walkAmount;

		if (walkAmount < 0.05F) {
			this.right_arm.xRot = 0.0F;
			this.left_arm.xRot = 0.0F;
			this.right_leg.xRot = 0.0F;
			this.left_leg.xRot = 0.0F;
		}
	}

	private void animateSwimmingArmRight(float cycle) {
		if (cycle < 0.25F) {
			float p = cycle / 0.25F;

			/*
			 * Fase 1:
			 * braço esticado para frente.
			 */
			this.right_arm.xRot = lerp(-1.85F, -1.35F, p);
			this.right_arm.yRot = lerp(0.00F, -0.10F, p);
			this.right_arm.zRot = lerp(0.05F, 0.10F, p);

		} else if (cycle < 0.55F) {
			float p = (cycle - 0.25F) / 0.30F;

			/*
			 * Fase 2:
			 * puxa para trás na linha horizontal do tronco.
			 */
			this.right_arm.xRot = lerp(-1.35F, 0.15F, p);
			this.right_arm.yRot = lerp(-0.10F, -0.18F, p);
			this.right_arm.zRot = lerp(0.10F, 0.08F, p);

		} else if (cycle < 0.75F) {
			float p = (cycle - 0.55F) / 0.20F;

			/*
			 * Fase 3:
			 * desce ao lado do corpo.
			 */
			this.right_arm.xRot = lerp(0.15F, 0.85F, p);
			this.right_arm.yRot = lerp(-0.18F, -0.05F, p);
			this.right_arm.zRot = lerp(0.08F, 0.22F, p);

		} else {
			float p = (cycle - 0.75F) / 0.25F;

			/*
			 * Fase 4:
			 * retorna para frente.
			 */
			this.right_arm.xRot = lerp(0.85F, -1.85F, p);
			this.right_arm.yRot = lerp(-0.05F, 0.00F, p);
			this.right_arm.zRot = lerp(0.22F, 0.05F, p);
		}
	}

	private void animateSwimmingArmLeft(float cycle) {
		if (cycle < 0.25F) {
			float p = cycle / 0.25F;

			/*
			 * Fase 1:
			 * braço esticado para frente.
			 */
			this.left_arm.xRot = lerp(-1.85F, -1.35F, p);
			this.left_arm.yRot = lerp(0.00F, 0.10F, p);
			this.left_arm.zRot = lerp(-0.05F, -0.10F, p);

		} else if (cycle < 0.55F) {
			float p = (cycle - 0.25F) / 0.30F;

			/*
			 * Fase 2:
			 * puxa para trás na linha horizontal do tronco.
			 */
			this.left_arm.xRot = lerp(-1.35F, 0.15F, p);
			this.left_arm.yRot = lerp(0.10F, 0.18F, p);
			this.left_arm.zRot = lerp(-0.10F, -0.08F, p);

		} else if (cycle < 0.75F) {
			float p = (cycle - 0.55F) / 0.20F;

			/*
			 * Fase 3:
			 * desce ao lado do corpo.
			 */
			this.left_arm.xRot = lerp(0.15F, 0.85F, p);
			this.left_arm.yRot = lerp(0.18F, 0.05F, p);
			this.left_arm.zRot = lerp(-0.08F, -0.22F, p);

		} else {
			float p = (cycle - 0.75F) / 0.25F;

			/*
			 * Fase 4:
			 * retorna para frente.
			 */
			this.left_arm.xRot = lerp(0.85F, -1.85F, p);
			this.left_arm.yRot = lerp(0.05F, 0.00F, p);
			this.left_arm.zRot = lerp(-0.22F, -0.05F, p);
		}
	}

	private float lerp(float start, float end, float progress) {
		return start + (end - start) * progress;
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