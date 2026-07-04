package hamud.mod.client;

import hamud.mod.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HamudModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.REMY, RemyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(RemyModel.LAYER_LOCATION, RemyModel::createBodyLayer);

        System.out.println("Hamud Mod Client carregado!");
    }
}