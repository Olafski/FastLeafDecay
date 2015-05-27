package com.olafski.fastleafdecay;

import net.minecraft.world.World;
import net.minecraft.block.Block;

import java.util.Random;

public class FldHandler {
    static Random rng = new Random();

    private static int baseDecayTime = FldConfiguration.minimumDecayTime;
    private static int randomizationTime = FldConfiguration.maximumDecayTime - FldConfiguration.minimumDecayTime;

    public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
    {
        worldObj.scheduleBlockUpdate(posX, posY, posZ, block, baseDecayTime + rng.nextInt(randomizationTime));

        return;
    }
}
