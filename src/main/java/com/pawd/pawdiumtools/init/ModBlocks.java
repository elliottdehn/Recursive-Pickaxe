package com.pawd.pawdiumtools.init;

import com.pawd.pawdiumtools.block.BlockPD;
import com.pawd.pawdiumtools.block.BlockPawdium;
import com.pawd.pawdiumtools.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockPD blockPawdium = new BlockPawdium();

    public static void init()
    {
        GameRegistry.registerBlock(blockPawdium, "blockPawdium");
    }
}
