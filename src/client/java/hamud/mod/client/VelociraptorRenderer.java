package hamud.mod.client;

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
}
