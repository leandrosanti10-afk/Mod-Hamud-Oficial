package hamud.mod.entity;

import hamud.mod.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
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
import org.jetbrains.annotations.Nullable;

public class RemyEntity extends Villager {

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
        offers.clear();

        int level = this.getVillagerData().getLevel();

        switch (level) {
            case 1 -> addLevel1Trades(offers);
            case 2 -> addLevel2Trades(offers);
            case 3 -> addLevel3Trades(offers);
            case 4 -> addLevel4Trades(offers);
            case 5 -> addLevel5Trades(offers);
            default -> addLevel1Trades(offers);
        }
    }

    private void addLevel1Trades(MerchantOffers offers) {
        // Remy compra itens de fazenda e paga com moeda hamud
        offers.add(new MerchantOffer(
                new ItemStack(Items.WHEAT, 20),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16, 2, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.CARROT, 15),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16, 2, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.POTATO, 15),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16, 2, 0.05f
        ));

        // Remy vende itens básicos por moeda hamud
        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                new ItemStack(Items.BREAD, 6),
                16, 1, 0.05f
        ));
    }

    private void addLevel2Trades(MerchantOffers offers) {
        offers.add(new MerchantOffer(
                new ItemStack(Items.BEETROOT, 18),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                16, 5, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.PUMPKIN, 10),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12, 5, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                new ItemStack(Items.APPLE, 8),
                12, 3, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 3),
                new ItemStack(Items.PUMPKIN_PIE, 4),
                12, 3, 0.05f
        ));
    }

    private void addLevel3Trades(MerchantOffers offers) {
        offers.add(new MerchantOffer(
                new ItemStack(Items.MELON, 12),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12, 10, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(Items.SUGAR_CANE, 20),
                new ItemStack(ModItems.MOEDA_HAMUD, 1),
                12, 10, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 4),
                new ItemStack(Items.GOLDEN_CARROT, 3),
                12, 5, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 4),
                new ItemStack(Items.COOKIE, 12),
                12, 5, 0.05f
        ));
    }

    private void addLevel4Trades(MerchantOffers offers) {
        offers.add(new MerchantOffer(
                new ItemStack(Items.HONEY_BOTTLE, 6),
                new ItemStack(ModItems.MOEDA_HAMUD, 2),
                10, 15, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 5),
                new ItemStack(Items.CAKE, 1),
                8, 8, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 6),
                new ItemStack(Items.GOLDEN_APPLE, 1),
                6, 10, 0.05f
        ));
    }

    private void addLevel5Trades(MerchantOffers offers) {
        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 8),
                new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 1),
                2, 30, 0.05f
        ));

        offers.add(new MerchantOffer(
                new ItemStack(ModItems.MOEDA_HAMUD, 7),
                new ItemStack(Items.GOLDEN_CARROT, 8),
                6, 20, 0.05f
        ));
    }

    @Override
    public VillagerData getVillagerData() {
        VillagerData data = super.getVillagerData();
        return data.setProfession(VillagerProfession.FARMER);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        this.setVillagerData(
                this.getVillagerData()
                        .setProfession(VillagerProfession.FARMER)
        );
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        RemyEntity baby = new RemyEntity((EntityType<? extends Villager>) this.getType(), level);
        baby.setVillagerData(
                baby.getVillagerData()
                        .setProfession(VillagerProfession.FARMER)
                        .setType(VillagerType.PLAINS)
        );
        return baby;
    }
}