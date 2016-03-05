package com.shoob.letsmodreboot.network;

import com.shoob.letsmodreboot.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This class is for custom messages that we send. For tileEntity descriptions use the DescriptionHandler
 */
public class NetworkHandler {
    private static SimpleNetworkWrapper INSTANCE;

    public static void init(){
        //Init the instance
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        //Register packets here:
        INSTANCE.registerMessage(MessageExplode.class, MessageExplode.class, 0, Side.SERVER);
    }

    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }
}
