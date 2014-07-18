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
    }
}
