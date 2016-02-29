package com.shoob.letsmodreboot.init;

import com.shoob.letsmodreboot.item.ItemFancyOrb;
import com.shoob.letsmodreboot.item.ItemLMR;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    public static ItemLMR fancyOrb;

    public static void init(){
        fancyOrb = new ItemFancyOrb();
    }

    public static void register(){
        GameRegistry.registerItem(fancyOrb, fancyOrb.getUnlocalizedName().substring(5));
    }

    public static void registerRenders(){
        fancyOrb.registerRender();
    }



}
