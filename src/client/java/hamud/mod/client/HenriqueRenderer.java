package hamud.mod.client;

import hamud.mod.HamudMod;
import hamud.mod.entity.HenriqueEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HenriqueRenderer extends MobRenderer<HenriqueEntity, HenriqueModel<HenriqueEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/henrique.png"
    );

    public HenriqueRenderer(EntityRendererProvider.Context context) {
        super(context, new HenriqueModel<>(context.bakeLayer(HenriqueModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(HenriqueEntity entity) {
        return TEXTURE;
    }
}