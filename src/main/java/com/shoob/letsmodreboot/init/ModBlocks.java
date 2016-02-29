package com.shoob.letsmodreboot.init;

import com.shoob.letsmodreboot.block.BlockFancyBlock;
import com.shoob.letsmodreboot.block.BlockLMR;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModBlocks {

    public static BlockLMR fancyBlock;

    public static void init(){
        fancyBlock = new BlockFancyBlock();
    }

    public static void register(){
        GameRegistry.registerBlock(fancyBlock, fancyBlock.getUnlocalizedName().substring(5));
    }

    public static void registerRenders(){
        fancyBlock.registerRender();
    }

}
