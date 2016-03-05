package com.shoob.letsmodreboot.proxy;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy {
    public abstract void registerRenders();

    public abstract void registerKeybindings();

    public abstract EntityPlayer getClientPlayer();
}
