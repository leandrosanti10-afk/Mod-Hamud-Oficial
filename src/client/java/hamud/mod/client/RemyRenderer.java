package hamud.mod.client;

import hamud.mod.HamudMod;
import hamud.mod.entity.RemyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RemyRenderer extends MobRenderer<RemyEntity, RemyModel<RemyEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(
            HamudMod.MOD_ID,
            "textures/entity/remy.png"
    );

    public RemyRenderer(EntityRendererProvider.Context context) {
        super(context, new RemyModel<>(context.bakeLayer(RemyModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RemyEntity entity) {
        return TEXTURE;
    }
}