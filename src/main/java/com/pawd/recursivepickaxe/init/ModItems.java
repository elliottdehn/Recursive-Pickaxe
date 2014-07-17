package com.pawd.recursivepickaxe.init;

import com.pawd.recursivepickaxe.item.ItemRP;
import com.pawd.recursivepickaxe.item.ItemRPTool;
import com.pawd.recursivepickaxe.item.ItemRecursivePickaxe;
import com.pawd.recursivepickaxe.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemRecursivePickaxe recursivePickaxe = new ItemRecursivePickaxe();

    public static void init()
    {
        GameRegistry.registerItem(recursivePickaxe, "recursivePickaxe");
    }
}
