package hamud.mod.client;

import hamud.mod.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HamudModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.REMY, RemyRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHABAN, ChabanRenderer::new);
        EntityRendererRegistry.register(ModEntities.VELOCIRAPTOR, VelociraptorRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(RemyModel.LAYER_LOCATION, RemyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ChabanModel.LAYER_LOCATION, ChabanModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(VelociraptorModel.LAYER_LOCATION, VelociraptorModel::createBodyLayer);

        System.out.println("Hamud Mod Client carregado!");
    }
}