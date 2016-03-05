package com.shoob.letsmodreboot.proxy;

import com.shoob.letsmodreboot.client.settings.KeyBindings;
import com.shoob.letsmodreboot.init.ModBlocks;
import com.shoob.letsmodreboot.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    public void registerRenders(){
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void registerKeybindings() {
        for(KeyBindings key : KeyBindings.values()){
            ClientRegistry.registerKeyBinding(key.getKeyBind());
        }
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }


}
