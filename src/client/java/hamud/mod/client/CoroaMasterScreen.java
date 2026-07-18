package hamud.mod.client;

import hamud.mod.menu.CoroaMasterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CoroaMasterScreen extends AbstractContainerScreen<CoroaMasterMenu> {

    private int selectedPower = 0;
    private ConfirmButton confirmButton;
    private ClearButton clearButton;

    private final List<PowerButton> powerButtons = new ArrayList<>();

    public CoroaMasterScreen(CoroaMasterMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        this.imageWidth = 230;
        this.imageHeight = 219;

        this.titleLabelY = 1000;
        this.inventoryLabelY = 1000;
    }

    private void addPowerButton(PowerButton button) {
        this.powerButtons.add(button);
        this.addRenderableWidget(button);
    }

    @Override
    protected void init() {
        super.init();

        this.powerButtons.clear();
        this.selectedPower = this.menu.getSelectedPower();

        int x = this.leftPos;
        int y = this.topPos;

        int paymentOffsetX = -10;

        int powerStartX = x + 22;
        int powerStartY = y + 52;
        int gapX = 27;
        int gapY = 30;

        this.addPowerButton(new PowerButton(
                powerStartX,
                powerStartY,
                CoroaMasterMenu.BUTTON_FORCA,
                new ItemStack(Items.IRON_SWORD),
                Component.translatable("screen.hamud-mod.coroa_master.power.strength")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX,
                powerStartY,
                CoroaMasterMenu.BUTTON_REGEN,
                new ItemStack(Items.GHAST_TEAR),
                Component.translatable("screen.hamud-mod.coroa_master.power.regeneration")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX * 2,
                powerStartY,
                CoroaMasterMenu.BUTTON_VISAO,
                new ItemStack(Items.ENDER_EYE),
                Component.translatable("screen.hamud-mod.coroa_master.power.vision")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX * 3,
                powerStartY,
                CoroaMasterMenu.BUTTON_VELOCIDADE,
                new ItemStack(Items.SUGAR),
                Component.translatable("screen.hamud-mod.coroa_master.power.speed")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX,
                powerStartY + gapY,
                CoroaMasterMenu.BUTTON_RESISTENCIA,
                new ItemStack(Items.SHIELD),
                Component.translatable("screen.hamud-mod.coroa_master.power.resistance")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX,
                powerStartY + gapY,
                CoroaMasterMenu.BUTTON_HASTE,
                new ItemStack(Items.GOLDEN_PICKAXE),
                Component.translatable("screen.hamud-mod.coroa_master.power.haste")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX * 2,
                powerStartY + gapY,
                CoroaMasterMenu.BUTTON_JUMP,
                new ItemStack(Items.RABBIT_FOOT),
                Component.translatable("screen.hamud-mod.coroa_master.power.jump")
        ));

        this.addPowerButton(new PowerButton(
                powerStartX + gapX * 3,
                powerStartY + gapY,
                CoroaMasterMenu.BUTTON_FOGO,
                new ItemStack(Items.MAGMA_CREAM),
                Component.translatable("screen.hamud-mod.coroa_master.power.fire")
        ));

        this.confirmButton = this.addRenderableWidget(new ConfirmButton(x + 176 + paymentOffsetX, y + 86));
        this.clearButton = this.addRenderableWidget(new ClearButton(x + 199 + paymentOffsetX, y + 86));
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        boolean creative = this.minecraft != null
                && this.minecraft.player != null
                && this.minecraft.player.getAbilities().instabuild;

        this.confirmButton.active = (this.menu.hasPayment() || creative) && this.selectedPower != 0;
        this.clearButton.active = true;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        int paymentOffsetX = -10;

        // Fundo externo
        guiGraphics.fill(x, y, x + this.imageWidth, y + this.imageHeight, 0xFF101010);

        // Moldura branca
        guiGraphics.fill(x + 2, y + 2, x + this.imageWidth - 2, y + this.imageHeight - 2, 0xFFEFEFEF);

        // Moldura cinza
        guiGraphics.fill(x + 5, y + 5, x + this.imageWidth - 5, y + this.imageHeight - 5, 0xFF8A8A8A);

        // Área do inventário
        guiGraphics.fill(x + 8, y + 118, x + this.imageWidth - 8, y + this.imageHeight - 8, 0xFFC6C6C6);

        // Painel escuro superior
        guiGraphics.fill(x + 8, y + 8, x + this.imageWidth - 8, y + 118, 0xFF202020);

        // Moldura interna do painel superior
        guiGraphics.fill(x + 12, y + 12, x + this.imageWidth - 12, y + 116, 0xFF3A3A3A);
        guiGraphics.fill(x + 14, y + 14, x + this.imageWidth - 14, y + 114, 0xFF1B1B1B);

        // Faixa do título
        guiGraphics.fill(x + 22, y + 11, x + this.imageWidth - 22, y + 35, 0xFF111111);

        // Barra cinza vertical separando poderes e pagamento
        guiGraphics.fill(x + 136, y + 44, x + 139, y + 110, 0xFFD0D0D0);

        // Quadrado visual do pagamento
        int paymentBoxX = x + 154 + paymentOffsetX;
        int paymentBoxY = y + 86;
        int paymentBoxSize = 18;

        guiGraphics.fill(
                paymentBoxX,
                paymentBoxY,
                paymentBoxX + paymentBoxSize,
                paymentBoxY + paymentBoxSize,
                0xFF4A4A4A
        );

        guiGraphics.fill(
                paymentBoxX + 1,
                paymentBoxY + 1,
                paymentBoxX + paymentBoxSize - 1,
                paymentBoxY + paymentBoxSize - 1,
                0xFFA8A8A8
        );

        this.drawAllSlots(guiGraphics);
    }

    private void drawAllSlots(GuiGraphics guiGraphics) {
        for (Slot slot : this.menu.slots) {
            // Não desenha o slot de pagamento aqui, porque ele já tem um quadrado custom 18x18.
            if (slot.index == 0) {
                continue;
            }

            this.drawSlot(guiGraphics, this.leftPos + slot.x - 1, this.topPos + slot.y - 1);
        }
    }

    private void drawSlot(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.fill(x, y, x + 18, y + 18, 0xFF373737);

        guiGraphics.fill(x, y, x + 18, y + 1, 0xFFFFFFFF);
        guiGraphics.fill(x, y, x + 1, y + 18, 0xFFFFFFFF);

        guiGraphics.fill(x + 17, y, x + 18, y + 18, 0xFF555555);
        guiGraphics.fill(x, y + 17, x + 18, y + 18, 0xFF555555);

        guiGraphics.fill(x + 1, y + 1, x + 17, y + 17, 0xFF8B8B8B);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int paymentOffsetX = -10;

        guiGraphics.drawCenteredString(
                this.font,
                Component.translatable("screen.hamud-mod.coroa_master.title"),
                this.imageWidth / 2,
                18,
                0xFFFFD700
        );

        guiGraphics.drawCenteredString(
                this.font,
                Component.translatable("screen.hamud-mod.coroa_master.powers"),
                74,
                39,
                0xFFE0E0E0
        );

        guiGraphics.drawCenteredString(
                this.font,
                Component.translatable("screen.hamud-mod.coroa_master.payment"),
                183 + paymentOffsetX,
                39,
                0xFFE0E0E0
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Tooltip vanilla dos itens/slots, incluindo o minério no slot de pagamento.
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        // Tooltip dos botões de poder.
        for (PowerButton button : this.powerButtons) {
            if (button.isHoveredOrFocused()) {
                guiGraphics.renderTooltip(
                        this.font,
                        button.getMessage(),
                        mouseX,
                        mouseY
                );
                return;
            }
        }

        // Tooltip do botão confirmar.
        if (this.confirmButton != null && this.confirmButton.isHoveredOrFocused()) {
            guiGraphics.renderTooltip(
                    this.font,
                    this.confirmButton.getMessage(),
                    mouseX,
                    mouseY
            );
            return;
        }

        // Tooltip do botão limpar.
        if (this.clearButton != null && this.clearButton.isHoveredOrFocused()) {
            guiGraphics.renderTooltip(
                    this.font,
                    this.clearButton.getMessage(),
                    mouseX,
                    mouseY
            );
        }
    }

    private class PowerButton extends AbstractButton {
        private final int powerId;
        private final ItemStack icon;

        public PowerButton(int x, int y, int powerId, ItemStack icon, Component tooltip) {
            super(x, y, 24, 24, tooltip);
            this.powerId = powerId;
            this.icon = icon;
        }

        @Override
        public void onPress() {
            selectedPower = this.powerId;

            if (minecraft != null && minecraft.gameMode != null) {
                minecraft.gameMode.handleInventoryButtonClick(menu.containerId, this.powerId);
            }
        }

        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            int outer = 0xFF555555;
            int inner = 0xFF242424;

            if (selectedPower == this.powerId) {
                outer = 0xFF6FBF4A;
                inner = 0xFF274F1F;
            } else if (this.isHoveredOrFocused()) {
                outer = 0xFFAAAAAA;
                inner = 0xFF343434;
            }

            guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0xFF000000);
            guiGraphics.fill(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1, outer);
            guiGraphics.fill(this.getX() + 3, this.getY() + 3, this.getX() + this.width - 3, this.getY() + this.height - 3, inner);

            guiGraphics.renderItem(this.icon, this.getX() + 4, this.getY() + 4);
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }

    private class ConfirmButton extends AbstractButton {
        public ConfirmButton(int x, int y) {
            super(x, y, 18, 18, Component.translatable("screen.hamud-mod.coroa_master.confirm"));
        }

        @Override
        public void onPress() {
            if (minecraft != null && minecraft.gameMode != null) {
                minecraft.gameMode.handleInventoryButtonClick(menu.containerId, CoroaMasterMenu.BUTTON_CONFIRM);
            }
        }

        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            int outer = this.active ? 0xFF3E7A3E : 0xFF2A2A2A;
            int inner = this.active ? 0xFF1E4D1E : 0xFF111111;

            guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0xFF000000);
            guiGraphics.fill(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1, outer);
            guiGraphics.fill(this.getX() + 3, this.getY() + 3, this.getX() + this.width - 3, this.getY() + this.height - 3, inner);

            guiGraphics.drawCenteredString(
                    CoroaMasterScreen.this.font,
                    "V",
                    this.getX() + 9,
                    this.getY() + 5,
                    0xFFFFFFFF
            );
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }

    private class ClearButton extends AbstractButton {
        public ClearButton(int x, int y) {
            super(x, y, 18, 18, Component.translatable("screen.hamud-mod.coroa_master.clear"));
        }

        @Override
        public void onPress() {
            selectedPower = 0;

            if (minecraft != null && minecraft.gameMode != null) {
                minecraft.gameMode.handleInventoryButtonClick(menu.containerId, CoroaMasterMenu.BUTTON_CLEAR);
            }
        }

        @Override
        protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            int outer = 0xFF7A3E3E;
            int inner = 0xFF4D1E1E;

            guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 0xFF000000);
            guiGraphics.fill(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1, outer);
            guiGraphics.fill(this.getX() + 3, this.getY() + 3, this.getX() + this.width - 3, this.getY() + this.height - 3, inner);

            guiGraphics.drawCenteredString(
                    CoroaMasterScreen.this.font,
                    "X",
                    this.getX() + 9,
                    this.getY() + 5,
                    0xFFFFFFFF
            );
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
            this.defaultButtonNarrationText(narrationElementOutput);
        }
    }
}