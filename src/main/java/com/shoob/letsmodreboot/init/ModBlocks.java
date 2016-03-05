package com.shoob.letsmodreboot.init;

import com.shoob.letsmodreboot.block.BlockCamoMine;
import com.shoob.letsmodreboot.block.BlockFancyBlock;
import com.shoob.letsmodreboot.block.BlockLMR;
import com.shoob.letsmodreboot.block.BlockLMRTileEntity;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModBlocks {

    public static BlockLMR fancyBlock;
    public static BlockLMRTileEntity camoMine;

    public static void init(){
        fancyBlock = new BlockFancyBlock();
        camoMine = new BlockCamoMine();
    }


    public static void register(){
        GameRegistry.registerBlock(fancyBlock, fancyBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(camoMine, camoMine.getUnlocalizedName().substring(5));
    }

    public static void registerRenders(){
        fancyBlock.registerRender();
    }

}
