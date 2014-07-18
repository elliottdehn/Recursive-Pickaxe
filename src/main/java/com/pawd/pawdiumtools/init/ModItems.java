package com.pawd.pawdiumtools.init;

import com.pawd.pawdiumtools.item.ItemPawdium;
import com.pawd.pawdiumtools.item.ItemPawdiumAxe;
import com.pawd.pawdiumtools.item.ItemPawdiumPickaxe;
import com.pawd.pawdiumtools.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemPawdiumPickaxe pawdiumPickaxe = new ItemPawdiumPickaxe();
    public static final ItemPawdiumAxe pawdiumAxe = new ItemPawdiumAxe();

    public static final ItemPawdium pawdium = new ItemPawdium();

    public static void init()
    {
        GameRegistry.registerItem(pawdiumPickaxe, "pawdiumPickaxe");
        GameRegistry.registerItem(pawdiumAxe, "pawdiumAxe");

        GameRegistry.registerItem(pawdium, "pawdium");
    }
}
