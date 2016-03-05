package com.shoob.letsmodreboot.event;

import com.shoob.letsmodreboot.init.ModItems;
import com.shoob.letsmodreboot.util.LogHelper;
import jline.internal.Log;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

public class LMREventHandler {

    @SubscribeEvent
    public void addPigDrops(LivingDropsEvent event){
        if(event.entityLiving instanceof EntityPig && event.entityLiving.getRNG().nextInt(3) == 0) {
            ItemStack stack = new ItemStack(ModItems.fancyOrb);
            event.drops.add(
                    new EntityItem(event.entityLiving.worldObj,
                            event.entityLiving.getPosition().getX(),
                            event.entityLiving.getPosition().getY(),
                            event.entityLiving.getPosition().getZ(),
                            stack));
        }
        Log.info("Entity drops items");
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.side == Side.SERVER && event.phase == TickEvent.Phase.END){
            BlockPos pos = event.player.getPosition();
            List<EntityLivingBase> entities = event.player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.fromBounds(pos.getX() - 3, pos.getY() - 3, pos.getZ() - 3, pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4));
            for(Entity entity : entities){
                if(entity != event.player){
                    entity.setVelocity(0, 1, 0);
                }
            }
//            LogHelper.info("Player " + event.player.getDisplayNameString() + " ticked " + event.phase);
        }
    }
}
