package com.shoob.letsmodreboot.network;

import com.shoob.letsmodreboot.LetsModReboot;
import com.shoob.letsmodreboot.reference.Reference;
import com.shoob.letsmodreboot.tileentity.TileEntityLMR;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

@ChannelHandler.Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {

    public static final String CHANNEL = Reference.MOD_ID + ".Description";

    static{
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
    }

    public static void init(){
        //NOOP
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
        PacketBuffer buf = new PacketBuffer(msg.payload());
        BlockPos pos = buf.readBlockPos();

        TileEntity te = LetsModReboot.proxy.getClientPlayer().worldObj.getTileEntity(pos); //TODO probably will NullPointer exception
        if(te instanceof TileEntityLMR){
            ((TileEntityLMR) te).readFromPacket(buf);
        }
    }
}
