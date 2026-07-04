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

    private final ModelPart bb_main;

    public RemyModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main",
                CubeListBuilder.create()
                        .texOffs(0, 45).addBox(-3.0F, -12.0F, 0.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 29).addBox(-3.0F, -24.0F, -4.0F, 4.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 45).addBox(-3.0F, -24.0F, 4.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 29).addBox(-5.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 49).addBox(-4.0F, -21.0F, 4.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 49).addBox(-4.0F, -21.0F, -9.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(16, 45).addBox(-3.0F, -24.0F, -8.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 45).addBox(-3.0F, -12.0F, -4.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 16).addBox(-2.0F, -13.0F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 55).addBox(-3.0F, -2.0F, -4.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(52, 55).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(56, 44).addBox(3.0F, -27.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 59).addBox(2.0F, -25.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0).addBox(-9.0F, -31.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 16).addBox(-6.0F, -34.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
                        .texOffs(40, 25).addBox(3.0F, -26.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        bb_main.addOrReplaceChild("cube_r1",
                CubeListBuilder.create()
                        .texOffs(56, 25).addBox(0.0F, -18.0F, -1.0F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.0F, -8.0F, -6.0F, 0.0F, 0.0F, 0.6545F)
        );

        bb_main.addOrReplaceChild("cube_r2",
                CubeListBuilder.create()
                        .texOffs(44, 59).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(44, 59).addBox(0.0F, -6.0F, -4.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(44, 59).addBox(0.0F, -6.0F, -7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                        .texOffs(34, 51).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(7.0F, -22.0F, -3.0F, 0.0F, 0.0F, 0.6545F)
        );

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Sem animação por enquanto.
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
        this.bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}