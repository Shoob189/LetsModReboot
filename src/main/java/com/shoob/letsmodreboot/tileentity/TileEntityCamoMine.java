package com.shoob.letsmodreboot.tileentity;


import com.shoob.letsmodreboot.init.ModBlocks;
import com.shoob.letsmodreboot.util.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.List;

public class TileEntityCamoMine extends TileEntityLMR implements ITickable, ISidedInventory{

    private int timer = 60;
    private ItemStack[] camoStack = new ItemStack[6];

    @Override
    public void update() {
        if(timer > 0) timer--;
        else if(!worldObj.isRemote){
            List<EntityPlayer> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.fromBounds(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2));
            if(!entities.isEmpty()) {
                worldObj.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3.0F, true);
            }
        }
    }

    public void setCamoStack(ItemStack stack, int side){
        LogHelper.info("Before: " + camoStack[side]);
        setInventorySlotContents(side, stack);
        LogHelper.info("After: " + camoStack[side]);

    }

    public ItemStack getCamoStack(int side){
        return getStackInSlot(side);
    }

    @Override
    public void writeToPacket(ByteBuf buf){
        for (ItemStack stack : camoStack)
            ByteBufUtils.writeItemStack(buf, stack);
    }

    @Override
    public void readFromPacket(ByteBuf buf){
        for(int i = 0; i < camoStack.length; i++) {
            camoStack[i] = ByteBufUtils.readItemStack(buf);
        }
        worldObj.markBlockRangeForRenderUpdate(pos, pos);
    }



    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        timer = tag.getInteger("timer");

        NBTTagList camoStackTag = tag.getTagList("camoStacks", 10);

        camoStack = new ItemStack[6];
        for(int i = 0; i < camoStackTag.tagCount(); i++){
            NBTTagCompound t = camoStackTag.getCompoundTagAt(i);
            int index = t.getByte("index");
            if(index >= 0 && index < camoStack.length){
                camoStack[index] = ItemStack.loadItemStackFromNBT(t);
            }
            else{
                camoStack = null;
            }
        }


    }

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setInteger("timer", timer);

        NBTTagList camoStackTag = new NBTTagList();
        for(int i = 0; i < camoStack.length; i++){
            ItemStack stack = camoStack[i];
            if(stack != null) {
                NBTTagCompound t = new NBTTagCompound();
                stack.writeToNBT(t);
                t.setByte("index", (byte)i);
                camoStackTag.appendTag(t);
            }
        }
        tag.setTag("camoStacks", camoStackTag);
    }




    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return camoStack.length;
    }

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.camoStack[index];
    }

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.camoStack[index] != null)
        {
            if (this.camoStack[index].stackSize <= count)
            {
                ItemStack itemstack1 = this.camoStack[index];
                this.camoStack[index] = null;
                this.markDirty();
                return itemstack1;
            }
            else
            {
                ItemStack itemstack = this.camoStack[index].splitStack(count);

                if (this.camoStack[index].stackSize == 0)
                {
                    this.camoStack[index] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack removeStackFromSlot(int index)
    {
        if (this.camoStack[index] != null)
        {
            ItemStack itemstack = this.camoStack[index];
            this.camoStack[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.camoStack[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        worldObj.markBlockForUpdate(pos);
        this.markDirty();
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return ModBlocks.camoMine.getUnlocalizedName();
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));

    }


    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return stack != null && stack.getItem() instanceof ItemBlock;
    }

    public int getField(int id)
    {
        return 0;
    }

    public void setField(int id, int value)
    {
    }

    public int getFieldCount()
    {
        return 0;
    }

    public void clear()
    {
        for (int i = 0; i < this.camoStack.length; ++i)
        {
            this.camoStack[i] = null;
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[]{ side.getIndex()} ;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return index == direction.getIndex() && isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }
}
