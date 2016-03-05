package com.shoob.letsmodreboot.gui;

import com.shoob.letsmodreboot.inventory.ContainerCamoMine;
import com.shoob.letsmodreboot.network.MessageHandleGuiButtonPressed;
import com.shoob.letsmodreboot.network.NetworkHandler;
import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCamoMine extends GuiLMR{
    private final TileEntityCamoMine te;
    private GuiButton resetButton;

    public GuiCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te){
        super(new ContainerCamoMine(playerInventory, te), "camoMine", te);
        this.te = te;
    }

    public void initGui(){
        super.initGui();
        resetButton = new GuiButton(0, guiLeft + 10, guiTop + 37, 40, 20, I18n.format("gui.letsmodreboot.camoMine.button.reset"));
        buttonList.add(resetButton);
    }

    @Override
    protected void actionPerformed(GuiButton button){
        if(button.id == 0){
            NetworkHandler.sendToServer(new MessageHandleGuiButtonPressed(te,button.id));
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);                                                                  //0xAARRGGBB
        this.fontRendererObj.drawString(I18n.format("gui.letsmodreboot.camoMine.timer",te.getTimer()), 10, 25,0xFF444444);

    }

    @Override
    public void updateScreen(){
        super.updateScreen();
        resetButton.displayString = I18n.format("gui.letsmodreboot.camoMine.button." + (te.getTimer() == -1 ? "restart" : "reset"));
    }


}
