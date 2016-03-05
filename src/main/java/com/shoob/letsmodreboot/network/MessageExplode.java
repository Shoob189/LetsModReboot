package com.shoob.letsmodreboot.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class MessageExplode extends MessageBase<MessageExplode> {

    private float explosionSize; //3.0F is a creeper sized explosion

    public MessageExplode(){}

    public MessageExplode(float explosionSize){
        this.explosionSize = explosionSize;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        explosionSize = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(explosionSize);
    }

    @Override
    public void handleClientSide(MessageExplode message, EntityPlayer player) {

    }

    @Override
    public void handleServerSide(MessageExplode message, EntityPlayer player) {
        BlockPos pos = player.getPosition();
        player.worldObj.createExplosion(player, pos.getX(), pos.getY() - 10, pos.getZ(), message.explosionSize, true);
    }
}
