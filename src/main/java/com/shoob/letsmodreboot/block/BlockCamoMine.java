package com.shoob.letsmodreboot.block;

import com.shoob.letsmodreboot.LetsModReboot;
import com.shoob.letsmodreboot.handler.GuiHandler;
import com.shoob.letsmodreboot.init.ModItems;
import com.shoob.letsmodreboot.item.ItemLMR;
import com.shoob.letsmodreboot.reference.Reference;
import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import com.shoob.letsmodreboot.util.LogHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCamoMine extends BlockLMRTileEntity{
    public BlockCamoMine(){
        this.setUnlocalizedName("camoMine");
        setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCamoMine();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote) {
            if (playerIn.isSneaking()) {
                playerIn.openGui(LetsModReboot.instance, GuiHandler.GuiIDs.CAMO_MINE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            } else {

                TileEntityCamoMine te = (TileEntityCamoMine) worldIn.getTileEntity(pos);
                if (te.getCamoStack(side.getIndex()) != null) {
                    ItemStack camoStack = te.getCamoStack(side.getIndex());
                    te.setCamoStack(null, side.getIndex());
                    EntityItem itemEntity = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), camoStack);
                    worldIn.spawnEntityInWorld(itemEntity);
                } else {
                    ItemStack playerItem = playerIn.getCurrentEquippedItem();
                    if (playerItem != null) {
                        ItemStack camoStack = playerItem.splitStack(1);
                        te.setCamoStack(camoStack, side.getIndex());
                    }
                }
            }
        }
        return true;
    }
}
