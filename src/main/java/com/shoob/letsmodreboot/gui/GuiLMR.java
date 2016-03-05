package com.shoob.letsmodreboot.gui;

import com.shoob.letsmodreboot.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public abstract class GuiLMR extends GuiContainer {
    private ResourceLocation guiTexture;
    private IInventory inventory;

    public GuiLMR(Container container, String guiTextureName, IInventory inventory){
        super(container);
        guiTexture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + guiTextureName + ".png");
        this.inventory = inventory;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(guiTexture);
        this.drawTexturedModalRect(guiLeft, guiTop, 0,0, xSize, ySize);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = I18n.format(this.inventory.getDisplayName().getUnformattedText() +".name");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
