package com.shoob.letsmodreboot.handler;

import com.shoob.letsmodreboot.gui.GuiCamoMine;
import com.shoob.letsmodreboot.inventory.ContainerCamoMine;
import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public enum GuiIDs{
        CAMO_MINE;
    }


    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiIDs.values()[ID]){
            case CAMO_MINE:
                return new ContainerCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(new BlockPos(x,y,z)));
        }
        throw new IllegalArgumentException("No gui with id " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(GuiIDs.values()[ID]){
            case CAMO_MINE:
                return new GuiCamoMine(player.inventory, (TileEntityCamoMine)world.getTileEntity(new BlockPos(x,y,z)));
        }
        throw new IllegalArgumentException("No gui with id " + ID);    }
}
