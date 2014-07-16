package com.pawd.recursivepickaxe.init;

import com.pawd.recursivepickaxe.item.ItemRC;
import com.pawd.recursivepickaxe.item.ItemRecursivePickaxe;
import com.pawd.recursivepickaxe.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemRC recursivePickaxe = new ItemRecursivePickaxe();

    public static void init()
    {
        GameRegistry.registerItem(recursivePickaxe, "recursivePickaxe");
    }
}
