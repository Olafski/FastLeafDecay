package com.olafski.fastleafdecay;

import net.minecraft.world.World;
import net.minecraft.block.Block;

import java.util.Random;

public class FldHandler {
    static Random rng = new Random();

    public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
    {
            worldObj.scheduleBlockUpdate(posX, posY, posZ, block, 4 + rng.nextInt(7));
            return;
    }
}
