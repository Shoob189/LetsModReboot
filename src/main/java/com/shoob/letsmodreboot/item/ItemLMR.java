package com.shoob.letsmodreboot.item;

import com.shoob.letsmodreboot.creativetab.CreativeTabLMR;
import com.shoob.letsmodreboot.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLMR extends Item {

    public ItemLMR(){
        super();
        this.setCreativeTab(CreativeTabLMR.LMR_TAB);
    }


    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return getUnlocalizedName();
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    protected String getUnwrappedUnlocalizedName(){
        return getUnwrappedUnlocalizedName(getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public void registerRender(){

        Minecraft.getMinecraft()
                .getRenderItem()
                .getItemModelMesher()
                .register(
                        this,
                        0,
                        new ModelResourceLocation(getUnwrappedUnlocalizedName(), "inventory")
                );
    }
}

