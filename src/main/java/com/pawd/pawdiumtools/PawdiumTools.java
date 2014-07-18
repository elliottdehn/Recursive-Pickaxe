package com.pawd.pawdiumtools;

import com.pawd.pawdiumtools.handler.ConfigurationHandler;
import com.pawd.pawdiumtools.init.ModBlocks;
import com.pawd.pawdiumtools.init.ModItems;
import com.pawd.pawdiumtools.init.ModRecipes;
import com.pawd.pawdiumtools.proxy.IProxy;
import com.pawd.pawdiumtools.reference.Reference;
import com.pawd.pawdiumtools.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)

public class PawdiumTools
{
    @Mod.Instance(Reference.MOD_ID)
    public static PawdiumTools instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        ModItems.init();
        ModBlocks.init();
        ModRecipes.init();

        LogHelper.info("Pre-Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        LogHelper.info("Post-Initialization Complete!");
    }
}
