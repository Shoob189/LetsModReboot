package com.shoob.letsmodreboot.client.handler;

import com.shoob.letsmodreboot.client.settings.KeyBindings;
import com.shoob.letsmodreboot.reference.Key;
import com.shoob.letsmodreboot.util.LogHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler {

    private static Key getPressedKeyBinding(){
        if(KeyBindings.charge.isPressed()){
            return Key.CHARGE;
        }
        else if(KeyBindings.release.isPressed()){
            return Key.RELEASE;
        }


        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event){
        LogHelper.info(getPressedKeyBinding());
    }
}
