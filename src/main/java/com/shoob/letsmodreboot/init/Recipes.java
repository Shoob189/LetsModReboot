package com.shoob.letsmodreboot.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {
    public static void init()
    {

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack( ModItems.fancyOrb), " s ", "sss", " s ", 's', "stickWood"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fancyBlock), new ItemStack(ModItems.fancyOrb), new ItemStack(ModItems.fancyOrb)));
    }
}
