package hamud.mod.client;

import hamud.mod.HamudMod;
import hamud.mod.entity.BranoEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BranoRenderer extends MobRenderer<BranoEntity, BranoModel<BranoEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/brano.png"
    );

    public BranoRenderer(EntityRendererProvider.Context context) {
        super(context, new BranoModel<>(context.bakeLayer(BranoModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BranoEntity entity) {
        return TEXTURE;
    }
}