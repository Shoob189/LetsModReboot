package com.shoob.letsmodreboot.handler;

import com.shoob.letsmodreboot.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;
    public static boolean testValue = false;

    public static void init(File configFile){
        if(configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }


    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfiguration();
        }
    }

    public static void loadConfiguration(){
        testValue = configuration.getBoolean("testValue", Configuration.CATEGORY_GENERAL, true, "This is an example config value");

        if ( configuration.hasChanged()) {
            configuration.save();
        }
    }

}
