package com.shoob.letsmodreboot.tileentity;

import com.shoob.letsmodreboot.network.DescriptionHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public abstract class TileEntityLMR extends TileEntity {

    public Packet getDescriptionPacket(){
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeBlockPos(pos);
        writeToPacket(buf);
        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
    }

    public void writeToPacket(ByteBuf buf){

    }

    public void readFromPacket(ByteBuf buf){}

    public void onGuiButtonPress(int id){}
}
