package com.shoob.letsmodreboot;

import com.shoob.letsmodreboot.client.handler.KeyInputEventHandler;
import com.shoob.letsmodreboot.handler.ConfigurationHandler;
import com.shoob.letsmodreboot.handler.GuiHandler;
import com.shoob.letsmodreboot.init.ModBlocks;
import com.shoob.letsmodreboot.init.ModItems;
import com.shoob.letsmodreboot.init.ModTileEntities;
import com.shoob.letsmodreboot.init.Recipes;
import com.shoob.letsmodreboot.network.DescriptionHandler;
import com.shoob.letsmodreboot.network.NetworkHandler;
import com.shoob.letsmodreboot.proxy.IProxy;
import com.shoob.letsmodreboot.reference.Reference;
import com.shoob.letsmodreboot.util.LogHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid= Reference.MOD_ID, name= Reference.MOD_NAME, version= Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class LetsModReboot {

    @Mod.Instance(Reference.MOD_ID)
    public static LetsModReboot instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
        proxy.registerKeybindings();

        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();
        ModTileEntities.init();

        NetworkHandler.init();
        DescriptionHandler.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        LogHelper.info("Pre-Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
        proxy.registerRenders();
        Recipes.init();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        LogHelper.info("Post-Initialization Complete!");
    }

}
