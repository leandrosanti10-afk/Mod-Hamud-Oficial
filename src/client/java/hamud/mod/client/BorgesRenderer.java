package hamud.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import hamud.mod.HamudMod;
import hamud.mod.entity.BorgesEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BorgesRenderer extends MobRenderer<BorgesEntity, BorgesModel<BorgesEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/borges.png"
    );

    public BorgesRenderer(EntityRendererProvider.Context context) {
        super(context, new BorgesModel<>(context.bakeLayer(BorgesModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(BorgesEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(
            BorgesEntity entity,
            PoseStack poseStack,
            float bob,
            float yBodyRot,
            float partialTick
    ) {
        super.setupRotations(entity, poseStack, bob, yBodyRot, partialTick);

        if (entity.isInWater()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-80.0F));
            poseStack.translate(0.0D, -0.30D, 0.20D);
        }
    }
}