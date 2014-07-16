package com.pawd.recursivepickaxe.init;

import com.pawd.recursivepickaxe.block.BlockPickaxe;
import com.pawd.recursivepickaxe.block.BlockRP;
import com.pawd.recursivepickaxe.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockRP blockPickaxe = new BlockPickaxe();

    public static void init()
    {
        GameRegistry.registerBlock(blockPickaxe, "blockPickaxe");
    }
}
