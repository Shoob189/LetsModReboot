package com.shoob.letsmodreboot.client.handler;

import com.shoob.letsmodreboot.client.settings.KeyBindings;
import com.shoob.letsmodreboot.network.MessageExplode;
import com.shoob.letsmodreboot.network.NetworkHandler;
import com.shoob.letsmodreboot.util.LogHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler {

    private static KeyBindings getPressedKey(){
        for(KeyBindings key : KeyBindings.values()){
            if(key.isPressed()) {
                return key;
            }
        }
        return null;
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        KeyBindings key = getPressedKey();
        if (key != null) {
            switch (key) {
                case EXPLODE:
                    NetworkHandler.sendToServer(new MessageExplode(3.0F)); //Big explosions (15.0+) crash with a concurrent error
                    break;
            }

            LogHelper.info(key);
        }
    }
}
