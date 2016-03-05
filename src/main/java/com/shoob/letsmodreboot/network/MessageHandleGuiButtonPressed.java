package com.shoob.letsmodreboot.network;

import com.shoob.letsmodreboot.tileentity.TileEntityLMR;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class MessageHandleGuiButtonPressed extends MessageBase<MessageHandleGuiButtonPressed> {
    private int x, y, z, id;

    public MessageHandleGuiButtonPressed(){

    }

    public MessageHandleGuiButtonPressed(TileEntityLMR te, int id){
        this.x = te.getPos().getX();
        this.y = te.getPos().getY();
        this.z = te.getPos().getZ();
        this.id = id;
    }

    @Override
    public void handleClientSide(MessageHandleGuiButtonPressed message, EntityPlayer player) {

    }

    @Override
    public void handleServerSide(MessageHandleGuiButtonPressed message, EntityPlayer player) {
        TileEntity te = player.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z));
        if(te instanceof TileEntityLMR){
            ((TileEntityLMR) te).onGuiButtonPress(message.id);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        id = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(id);
    }
}
