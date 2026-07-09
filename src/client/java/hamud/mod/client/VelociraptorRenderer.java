package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import hamud.mod.HamudMod;
import hamud.mod.entity.VelociraptorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VelociraptorRenderer extends MobRenderer<VelociraptorEntity, VelociraptorModel<VelociraptorEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/velociraptor.png"
    );

    public VelociraptorRenderer(EntityRendererProvider.Context context) {
        super(context, new VelociraptorModel<>(context.bakeLayer(VelociraptorModel.LAYER_LOCATION)), 0.45f);
    }

    @Override
    public ResourceLocation getTextureLocation(VelociraptorEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(VelociraptorEntity entity, PoseStack poseStack, float partialTickTime) {
        if (entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1.0f, 1.0f, 1.0f);
        }
    }
}