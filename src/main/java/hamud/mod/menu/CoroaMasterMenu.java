package hamud.mod.menu;

import hamud.mod.ModItems;
import hamud.mod.ModMenus;
import hamud.mod.item.CoroaMasterItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CoroaMasterMenu extends AbstractContainerMenu {

    public static final int BUTTON_FORCA = 1;
    public static final int BUTTON_REGEN = 2;
    public static final int BUTTON_VISAO = 3;
    public static final int BUTTON_VELOCIDADE = 4;
    public static final int BUTTON_RESISTENCIA = 5;
    public static final int BUTTON_HASTE = 6;
    public static final int BUTTON_JUMP = 7;
    public static final int BUTTON_FOGO = 8;

    public static final int BUTTON_CONFIRM = 99;
    public static final int BUTTON_CLEAR = 100;

    private final Player player;
    private final SimpleContainer paymentContainer = new SimpleContainer(1);
    private final ContainerData data = new SimpleContainerData(1);

    public CoroaMasterMenu(int containerId, Inventory inventory) {
        super(ModMenus.COROA_MASTER_MENU, containerId);
        this.player = inventory.player;

        ItemStack coroa = getHeldCrown(this.player);
        int selected = 0;

        if (!coroa.isEmpty()) {
            selected = coroa.getOrCreateTag().getInt(CoroaMasterItem.MODE_TAG);
        }

        this.data.set(0, selected);
        this.addDataSlots(this.data);

        this.addSlot(new Slot(this.paymentContainer, 0, 144, 86) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isPaymentItem(stack);
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
    }

    public int getSelectedPower() {
        return this.data.get(0);
    }

    public boolean hasPayment() {
        return this.getSlot(0).hasItem();
    }

    @Override
    public boolean stillValid(Player player) {
        return !getHeldCrown(player).isEmpty();
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (id >= BUTTON_FORCA && id <= BUTTON_FOGO) {
            this.data.set(0, id);
            return true;
        }

        if (id == BUTTON_CLEAR) {
            this.data.set(0, 0);

            ItemStack coroa = getHeldCrown(player);

            if (!coroa.isEmpty()) {
                coroa.getOrCreateTag().putInt(CoroaMasterItem.MODE_TAG, CoroaMasterItem.MODE_NONE);
            }

            return true;
        }

        if (id == BUTTON_CONFIRM) {
            int selected = this.data.get(0);

            if (selected == 0) {
                return false;
            }

            ItemStack coroa = getHeldCrown(player);

            if (coroa.isEmpty()) {
                return false;
            }

            Slot paymentSlot = this.getSlot(0);

            if (!paymentSlot.hasItem() && !player.getAbilities().instabuild) {
                player.displayClientMessage(
                        Component.translatable("message.hamud-mod.coroa_master.need_payment"),
                        true
                );
                return false;
            }

            if (!player.getAbilities().instabuild) {
                paymentSlot.remove(1);
            }

            coroa.getOrCreateTag().putInt(CoroaMasterItem.MODE_TAG, selected);

            player.displayClientMessage(
                    Component.translatable("message.hamud-mod.coroa_master.activated"),
                    true
            );

            return true;
        }

        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemStack = stack.copy();

            if (index == 0) {
                if (!this.moveItemStackTo(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (isPaymentItem(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 1 && index < 28) {
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 28 && index < 37) {
                    if (!this.moveItemStackTo(stack, 1, 28, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.clearContainer(player, this.paymentContainer);
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 36 + col * 18, 137 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 36 + i * 18, 195));
        }
    }

    private static ItemStack getHeldCrown(Player player) {
        if (player.getMainHandItem().is(ModItems.COROA_MASTER)) {
            return player.getMainHandItem();
        }

        if (player.getOffhandItem().is(ModItems.COROA_MASTER)) {
            return player.getOffhandItem();
        }

        return ItemStack.EMPTY;
    }

    public static boolean isPaymentItem(ItemStack stack) {
        return stack.is(Items.IRON_INGOT)
                || stack.is(Items.GOLD_INGOT)
                || stack.is(Items.DIAMOND)
                || stack.is(Items.EMERALD)
                || stack.is(Items.NETHERITE_INGOT)
                || stack.is(Items.COPPER_INGOT)
                || stack.is(Items.RAW_IRON)
                || stack.is(Items.RAW_GOLD)
                || stack.is(Items.RAW_COPPER)
                || stack.is(Items.REDSTONE)
                || stack.is(Items.LAPIS_LAZULI)
                || stack.is(Items.QUARTZ)
                || stack.is(Items.AMETHYST_SHARD)
                || stack.is(Items.COAL);
    }
}