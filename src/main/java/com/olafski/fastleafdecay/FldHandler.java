package com.olafski.fastleafdecay;

import net.minecraft.world.World;
import net.minecraft.block.Block;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FldHandler {
    static Random rng = new Random();

    private static int baseDecayTime = FldConfiguration.minimumDecayTime;
    private static int randomizationTime = FldConfiguration.maximumDecayTime - FldConfiguration.minimumDecayTime;

    public static void handleLeaveDecay(World worldObj, int posX, int posY, int posZ, Block block)
    {
        if (block.getLocalizedName().startsWith("tile.ore.berries")) { // Ignore Natura oreberries
            return;
        }

        //System.out.println("(" + posX + "," + posY + "," + posZ + "), block: " + block.getLocalizedName());
        worldObj.scheduleBlockUpdate(posX, posY, posZ, block, baseDecayTime + rng.nextInt(randomizationTime));

        return;
    }
}
