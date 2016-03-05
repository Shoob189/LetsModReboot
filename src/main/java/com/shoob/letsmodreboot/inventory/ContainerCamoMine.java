package com.shoob.letsmodreboot.inventory;

import com.shoob.letsmodreboot.tileentity.TileEntityCamoMine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCamoMine extends ContainerLMR {

    private final TileEntityCamoMine te;
    private int lastTimer = -1;

    public ContainerCamoMine(InventoryPlayer playerInventory, TileEntityCamoMine te){
        int x = 62;
        int y = 22;
        int slotSize = 18;

        this.addSlotToContainer(new SlotCamoflage(te, 0, x + slotSize        , y + slotSize * 2  ));
        this.addSlotToContainer(new SlotCamoflage(te, 1, x + slotSize        , y                 ));
        this.addSlotToContainer(new SlotCamoflage(te, 2, x + slotSize        , y + slotSize      ));
        this.addSlotToContainer(new SlotCamoflage(te, 3, x                   , y + slotSize      ));
        this.addSlotToContainer(new SlotCamoflage(te, 4, x + slotSize * 2    , y + slotSize      ));
        this.addSlotToContainer(new SlotCamoflage(te, 5, x + slotSize * 2 + 4, y - 4             ));

        this.addPlayerSlots(playerInventory, 8, 84);
        this.te = te;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.isUseableByPlayer(playerIn);
    }

    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();
        if(lastTimer != te.getTimer()){
            for(ICrafting crafter : crafters){
                crafter.sendProgressBarUpdate(this, 0, te.getTimer());
            }
            lastTimer = te.getTimer();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int val){
        super.updateProgressBar(id, val);
        if(id == 0){
            te.setTimer(val);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

           if(!te.isItemValidForSlot(0, itemstack1)){
                return null;
            }

            //TODO mergeItemStack does NOT check if the item is valid to go in the slot, DO NOT USE if slots are restricted (shouldn't be using it here)
            if (index < te.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1.splitStack(1), te.getSizeInventory(), 42, true))
                {
                    itemstack1.stackSize++; // add the one back in
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1.splitStack(1), 0, te.getSizeInventory(), false))
            {
                itemstack1.stackSize++; // add the one back in
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
}
