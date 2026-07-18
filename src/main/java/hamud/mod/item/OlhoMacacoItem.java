package hamud.mod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class OlhoMacacoItem extends Item {

    private static final double GLOW_RANGE = 16.0D;

    public OlhoMacacoItem(Properties properties) {
        super(properties);
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

        // Visão Noturna
        player.addEffect(new MobEffectInstance(
                MobEffects.NIGHT_VISION,
                260,
                0,
                true,
                false,
                true
        ));

        // Brilho em mobs próximos
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
}