package com.pawd.pawdiumtools.init;

import com.pawd.pawdiumtools.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModRecipes
{
    public static void init()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.pawdiumPickaxe), new Object[]
                {
                        "XXX",
                        " Y ",
                        " Y ",
                        'X', ModItems.pawdium,
                        'Y', Items.stick
                });

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.pawdiumAxe), new Object[]
                {
                        "XX ",
                        "XY ",
                        " Y ",
                        'X', ModItems.pawdium,
                        'Y', Items.stick
                });

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockPawdium), new Object[]
                {
                        "XXX",
                        "XXX",
                        "XXX",
                        'X', ModItems.pawdium
                });

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pawdium, 9), ModBlocks.blockPawdium);

    }
}
