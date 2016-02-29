package com.shoob.letsmodreboot.proxy;

import com.shoob.letsmodreboot.client.settings.KeyBindings;
import com.shoob.letsmodreboot.init.ModBlocks;
import com.shoob.letsmodreboot.init.ModItems;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    public void registerRenders(){
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(KeyBindings.charge);
        ClientRegistry.registerKeyBinding(KeyBindings.release);
    }


}
