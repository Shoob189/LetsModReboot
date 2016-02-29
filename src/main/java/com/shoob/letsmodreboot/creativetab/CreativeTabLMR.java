package com.shoob.letsmodreboot.creativetab;

import com.shoob.letsmodreboot.init.ModItems;
import com.shoob.letsmodreboot.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabLMR {

    public static final CreativeTabs LMR_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            return ModItems.fancyOrb;
        }

    };

}
