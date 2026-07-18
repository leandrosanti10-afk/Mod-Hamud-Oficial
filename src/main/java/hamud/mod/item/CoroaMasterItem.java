package hamud.mod.item;

import hamud.mod.menu.CoroaMasterMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import java.util.List;

public class CoroaMasterItem extends Item {

    public static final String MODE_TAG = "CoroaMasterMode";

    public static final int MODE_NONE = 0;
    public static final int MODE_FORCA = 1;
    public static final int MODE_REGENERACAO = 2;
    public static final int MODE_VISAO = 3;
    public static final int MODE_VELOCIDADE = 4;
    public static final int MODE_RESISTENCIA = 5;
    public static final int MODE_HASTE = 6;
    public static final int MODE_JUMP = 7;
    public static final int MODE_FOGO = 8;

    private static final double GLOW_RANGE = 32.0D;

    public CoroaMasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, inventory, p) -> new CoroaMasterMenu(containerId, inventory),
                    Component.translatable("screen.hamud-mod.coroa_master.title")
            ));
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slot,
            boolean selected
    ) {
        if (level.isClientSide) {
            return;
        }

        if (!(entity instanceof Player player)) {
            return;
        }

        boolean holdingInMainHand = player.getMainHandItem() == stack;
        boolean holdingInOffHand = player.getOffhandItem() == stack;

        if (!holdingInMainHand && !holdingInOffHand) {
            return;
        }

        int mode = stack.getOrCreateTag().getInt(MODE_TAG);

        switch (mode) {
            case MODE_FORCA -> player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_REGENERACAO -> player.addEffect(new MobEffectInstance(
                    MobEffects.REGENERATION,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_VISAO -> {
                player.addEffect(new MobEffectInstance(
                        MobEffects.NIGHT_VISION,
                        260,
                        0,
                        true,
                        false,
                        true
                ));

                List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(GLOW_RANGE),
                        livingEntity -> livingEntity.isAlive()
                                && livingEntity != player
                                && !(livingEntity instanceof Player)
                );

                for (LivingEntity livingEntity : nearbyEntities) {
                    livingEntity.addEffect(new MobEffectInstance(
                            MobEffects.GLOWING,
                            40,
                            0,
                            true,
                            false,
                            true
                    ));
                }
            }

            case MODE_VELOCIDADE -> player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_RESISTENCIA -> player.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_HASTE -> player.addEffect(new MobEffectInstance(
                    MobEffects.DIG_SPEED,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_JUMP -> player.addEffect(new MobEffectInstance(
                    MobEffects.JUMP,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            case MODE_FOGO -> player.addEffect(new MobEffectInstance(
                    MobEffects.FIRE_RESISTANCE,
                    40,
                    1,
                    true,
                    false,
                    true
            ));

            default -> {
            }
        }
    }
}