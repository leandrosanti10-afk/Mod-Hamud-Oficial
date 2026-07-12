package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import hamud.mod.HamudMod;
import hamud.mod.entity.ArpaoProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class ArpaoProjectileRenderer extends EntityRenderer<ArpaoProjectileEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/projectiles/arpao.png"
    );

    private final ArpaoProjectileModel<ArpaoProjectileEntity> model;

    public ArpaoProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ArpaoProjectileModel<>(context.bakeLayer(ArpaoProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            ArpaoProjectileEntity entity,
            float entityYaw,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight
    ) {
        poseStack.pushPose();

        float yaw = Mth.lerp(partialTick, entity.yRotO, entity.getYRot());
        float pitch = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());

        // Faz o projétil apontar para a direção do disparo
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(pitch));

        // Rotação extra por causa do eixo do modelo exportado do Blockbench
        poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));

        // Ajuste fino de posição
        poseStack.translate(0.0D, -1.42D, -0.08D);

        // Escala do projétil
        poseStack.scale(0.85F, 0.85F, 0.85F);

        this.model.renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ArpaoProjectileEntity entity) {
        return TEXTURE;
    }
}