package com.olafski.fastleafdecay;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FastLeafDecay.MODID, version = FastLeafDecay.VERSION, acceptableRemoteVersions = "*")
public class FastLeafDecay
{
    public static final String MODID = "fastleafdecay";
    public static final String VERSION = "1.3";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        FldConfiguration.init(event);
    }
}
