package com.shoob.letsmodreboot.handler;

import com.shoob.letsmodreboot.util.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;


    public static void init(File configFile){
        configuration = new Configuration(configFile);
        boolean configValue = false;

        try{
            //Create Config Obj from the file
            configuration.load();

            configValue = configuration.get(Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean();
        }
        catch(Exception e){
            //Log Exception
        }
        finally{
            //Save the file
            if ( configuration.hasChanged()) {
                configuration.save();
            }
        }
    }
}
