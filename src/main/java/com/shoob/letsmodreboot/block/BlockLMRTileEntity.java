package com.shoob.letsmodreboot.block;

import com.shoob.letsmodreboot.creativetab.CreativeTabLMR;
import com.shoob.letsmodreboot.reference.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockLMRTileEntity extends BlockContainer {


    public BlockLMRTileEntity(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(CreativeTabLMR.LMR_TAB);

    }

    public BlockLMRTileEntity(){
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    protected String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    protected String getUnwrappedUnlocalizedName(){
        return getUnwrappedUnlocalizedName(getUnlocalizedName());
    }


    @SideOnly(Side.CLIENT)
    public void registerRender(){
        Item item = Item.getItemFromBlock(this);
        Minecraft.getMinecraft()
                .getRenderItem()
                .getItemModelMesher()
                .register(
                        item,
                        0,
                        new ModelResourceLocation(getUnwrappedUnlocalizedName(), "inventory")
                );
    }
}
