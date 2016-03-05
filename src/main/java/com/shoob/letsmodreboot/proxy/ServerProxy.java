package com.shoob.letsmodreboot.proxy;

import net.minecraft.entity.player.EntityPlayer;

public class ServerProxy extends CommonProxy{

    @Override
    public void registerRenders(){
        //Do Nothing
    }

    @Override
    public void registerKeybindings() {
        //do nothing
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }


}
