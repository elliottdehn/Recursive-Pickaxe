package com.pawd.recursivepickaxe.handler;

import com.pawd.recursivepickaxe.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Gerhard on 7/16/2014.
 */
public class ConfigurationHandler
{
    public static Configuration configuration;
    public static boolean testValue = false;
    public static void init(File configFile)
    {
        //Make config object from file
        if(configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            //resync
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        testValue = configuration.getBoolean("configValue",Configuration.CATEGORY_GENERAL, false, "Example config value");

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }
}
