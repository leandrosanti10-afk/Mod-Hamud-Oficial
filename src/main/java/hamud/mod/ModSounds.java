package hamud.mod;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    
    public static final SoundEvent O_VOVIS_TA_INDECISO = registerSoundEvent("o_vovis_ta_indeciso");
    public static final SoundEvent O_VOVIS_VAI_COMER_A_EMILIA = registerSoundEvent("o_vovis_vai_comer_a_emilia");
    public static final SoundEvent O_VOVIS_E_HETERO_ELE_NAO_GOSTA_DE_PAU = registerSoundEvent("o_vovis_e_hetero_ele_nao_gosta_de_pau");
    public static final SoundEvent O_VOVIS_CARREGA_TUA_BOLSA = registerSoundEvent("o_vovis_carrega_tua_bolsa");
    public static final SoundEvent VOVIS_E_GAY_E_ELE_QUICA_NO_PAU = registerSoundEvent("vovis_e_gay_e_ele_quica_no_pau");
    public static final SoundEvent E_ANIVERSARIO_DO_BAFORA = registerSoundEvent("e_aniversario_do_bafora");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(HamudMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registrando sons do Hamud Mod...");
    }
}
