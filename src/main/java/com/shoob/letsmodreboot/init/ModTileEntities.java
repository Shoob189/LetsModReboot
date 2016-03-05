package com.shoob.letsmodreboot.init;

import com.shoob.letsmodreboot.reference.Names;
import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init(){
        GameRegistry.registerTileEntity(TileEntityCamoMine.class, Names.TileEntities.CAMO_MINE);
    }
}
