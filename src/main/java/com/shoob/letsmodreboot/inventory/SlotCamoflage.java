package com.shoob.letsmodreboot.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCamoflage extends Slot {
    public SlotCamoflage(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack){
        return this.inventory.isItemValidForSlot(getSlotIndex(), stack);
    }
}
