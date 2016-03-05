package com.shoob.letsmodreboot.gui;

import com.shoob.letsmodreboot.init.ModTileEntities;
import com.shoob.letsmodreboot.inventory.ContainerCamoMine;
import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCamoMine extends GuiLMR{


    public GuiCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te){
        super(new ContainerCamoMine(playerInventory, te), "camoMine", te);
    }


}
