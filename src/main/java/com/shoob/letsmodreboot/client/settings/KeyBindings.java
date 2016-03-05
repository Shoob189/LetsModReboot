package com.shoob.letsmodreboot.client.settings;

import com.shoob.letsmodreboot.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public enum KeyBindings
{
    CHARGE(Names.Keys.CHARGE, Keyboard.KEY_C),
    RELEASE(Names.Keys.RELEASE, Keyboard.KEY_R),
    EXPLODE(Names.Keys.EXPLODE, Keyboard.KEY_F);

//    public static KeyBinding charge = new KeyBinding(Names.Keys.CHARGE, Keyboard.KEY_C, Names.Keys.CATEGORY);
//    public static KeyBinding release = new KeyBinding(Names.Keys.RELEASE, Keyboard.KEY_R, Names.Keys.CATEGORY);

    private final KeyBinding keyBinding;

    private KeyBindings(String keyName, int defaultKeyCode){
        keyBinding = new KeyBinding(keyName, defaultKeyCode, Names.Keys.CATEGORY);
    }

    public KeyBinding getKeyBind(){
        return keyBinding;
    }

    public boolean isPressed(){
        return keyBinding.isPressed();
    }

};
