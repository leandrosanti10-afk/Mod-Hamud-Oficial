package hamud.mod.entity;

import hamud.mod.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class RemyEntity extends Villager {

    private BlockPos hamudmod$sleepTarget;
    private int hamudmod$sleepSearchCooldown = 0;
    public RemyEntity(EntityType<? extends Villager> entityType, Level level) {
        super(entityType, level);

        this.setVillagerData(
                this.getVillagerData()
                        .setProfession(VillagerProfession.FARMER)
                        .setType(VillagerType.PLAINS)
        );
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    protected void updateTrades() {
        MerchantOffers offers = this.getOffers();

        int level = this.getVillagerData().getLevel();

        int targetTradeCount = switch (level) {
            case 1 -> 2;
            case 2 -> 4;
            case 3 -> 6;
            case 4 -> 8;
            case 5 -> 10;
            default -> 2;
        };

        List<MerchantOffer> allTrades = createRemyTrades();

        for (int i = offers.size(); i < targetTradeCount && i < allTrades.size(); i++) {
            offers.add(allTrades.get(i));
        }
    }

    private List<MerchantOffer> createRemyTrades() {
        List<MerchantOffer> trades = new ArrayList<>();

        // =========================
        // NÍVEL 1 — 2 TROCAS
        // =========================

        // 20 trigo -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.WHEAT, 20),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                2,
                0.05f
        ));

        // 1 Moeda Hamud -> 6 pães
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.BREAD, 6),
                16,
                1,
                0.05f
        ));

        // =========================
        // NÍVEL 2 — TOTAL 4 TROCAS
        // =========================

        // 22 cenouras -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.CARROT, 22),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        // 26 batatas -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.POTATO, 26),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                5,
                0.05f
        ));

        // =========================
        // NÍVEL 3 — TOTAL 6 TROCAS
        // =========================

        // 15 beterrabas -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.BEETROOT, 15),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16,
                10,
                0.05f
        ));

        // 3 Moedas Hamud -> 4 tortas de abóbora
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.PUMPKIN_PIE, 4),
                12,
                10,
                0.05f
        ));

        // =========================
        // NÍVEL 4 — TOTAL 8 TROCAS
        // =========================

        // 10 abóboras -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.PUMPKIN, 10),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        // 4 melancias -> 1 Moeda Hamud
        trades.add(new MerchantOffer(
                new ItemStack(Items.MELON, 4),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12,
                15,
                0.05f
        ));

        // =========================
        // NÍVEL 5 — TOTAL 10 TROCAS
        // =========================

        // 3 Moedas Hamud -> 18 cookies
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.COOKIE, 18),
                12,
                20,
                0.05f
        ));

        // 6 Moedas Hamud -> 3 cenouras douradas
        trades.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 6),
                new ItemStack(Items.GOLDEN_CARROT, 3),
                8,
                30,
                0.05f
        ));

        return trades;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.setVillagerData(
                this.getVillagerData()
                        .setProfession(VillagerProfession.FARMER)
                        .setType(VillagerType.PLAINS)
        );
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }
    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            this.hamudmod$handleSleeping();
        }
    }

    private void hamudmod$handleSleeping() {
        // De dia, acorda
        if (this.level().isDay()) {
            this.hamudmod$sleepTarget = null;

            if (this.isSleeping()) {
                this.stopSleeping();
            }

            return;
        }

        // Se já está dormindo, não faz nada
        if (this.isSleeping()) {
            return;
        }

        // Se está negociando, não tenta dormir
        if (this.isTrading()) {
            return;
        }

        // Cooldown para não procurar cama todo tick
        if (this.hamudmod$sleepSearchCooldown > 0) {
            this.hamudmod$sleepSearchCooldown--;
        }

        // Procura cama se ainda não tiver alvo
        if (this.hamudmod$sleepTarget == null && this.hamudmod$sleepSearchCooldown <= 0) {
            this.hamudmod$sleepTarget = this.hamudmod$findNearbyBed();
            this.hamudmod$sleepSearchCooldown = 100;
        }

        // Se achou cama, anda até ela
        if (this.hamudmod$sleepTarget != null) {
            double distance = this.distanceToSqr(
                    this.hamudmod$sleepTarget.getX() + 0.5,
                    this.hamudmod$sleepTarget.getY(),
                    this.hamudmod$sleepTarget.getZ() + 0.5
            );

            // Se estiver longe, vai até a cama
            if (distance > 2.5) {
                this.getNavigation().moveTo(
                        this.hamudmod$sleepTarget.getX() + 0.5,
                        this.hamudmod$sleepTarget.getY(),
                        this.hamudmod$sleepTarget.getZ() + 0.5,
                        0.6
                );
            } else {
                // Se chegou perto, deita
                this.getNavigation().stop();
                this.startSleeping(this.hamudmod$sleepTarget);
            }
        }
    }

    private BlockPos hamudmod$findNearbyBed() {
        BlockPos origin = this.blockPosition();

        int radius = 16;

        for (BlockPos pos : BlockPos.betweenClosed(
                origin.offset(-radius, -3, -radius),
                origin.offset(radius, 3, radius)
        )) {
            BlockState state = this.level().getBlockState(pos);

            if (state.is(BlockTags.BEDS)) {
                return pos.immutable();
            }
        }

        return null;
    }
}

