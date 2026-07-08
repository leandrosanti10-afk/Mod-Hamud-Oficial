package hamud.mod.client;

import hamud.mod.HamudMod;
import hamud.mod.entity.ChabanEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ChabanRenderer extends MobRenderer<ChabanEntity, ChabanModel<ChabanEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/chaban.png"
    );

    public ChabanRenderer(EntityRendererProvider.Context context) {
        super(context, new ChabanModel<>(context.bakeLayer(ChabanModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ChabanEntity entity) {
        return TEXTURE;
    }
}