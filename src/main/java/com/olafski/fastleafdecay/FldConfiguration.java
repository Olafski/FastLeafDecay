package com.olafski.fastleafdecay;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FldConfiguration {
    public static Configuration config;

    public static int minimumDecayTime;
    public static int maximumDecayTime;

    public static void init(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.setCategoryComment("main", "There are 20 ticks in a second. Default settings are 4 minimum and 11 maximum decay time.");

        Property minimumDecayTimeProperty = config.get("main", "MinimumDecayTime", 4, "Minimum time in ticks for leaf decay. Must be lower than MaximumDecayTime!");
        Property maximumDecayTimeProperty = config.get("main", "MaximumDecayTime", 11, "Maximum time in ticks for leaf decay. Must be higher than MinimumDecayTime!");

        minimumDecayTime = minimumDecayTimeProperty.getInt();
        maximumDecayTime = maximumDecayTimeProperty.getInt();

        if (minimumDecayTime >= maximumDecayTime) {
            Logger logger = event.getModLog();
            logger.warn("MinimumDecayTime needs to be lower than MaximumDecayTime, resetting to default values!");

            minimumDecayTimeProperty.set(4);
            maximumDecayTimeProperty.set(11);

            minimumDecayTime = 4;
            maximumDecayTime = 11;
        }

        if (config.hasChanged()){
            config.save();
        }
    }
}
