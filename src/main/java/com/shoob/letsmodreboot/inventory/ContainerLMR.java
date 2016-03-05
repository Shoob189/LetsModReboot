package com.shoob.letsmodreboot.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerLMR extends Container {

    //Hint: Dispenser would use (8, 84) for (x, y))
    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y){
        for (int row = 0; row < 3; ++row)
        {
            for (int col = 0; col < 9; ++col)
            {
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9 , x + col * 18, y + row * 18));
            }
        }

        for (int slot = 0; slot < 9; ++slot)
        {
            this.addSlotToContainer(new Slot(playerInventory, slot, x + slot * 18, y + 58));
        }
    }
}
