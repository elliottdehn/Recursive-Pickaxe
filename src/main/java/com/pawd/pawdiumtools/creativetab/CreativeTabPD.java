package com.pawd.pawdiumtools.creativetab;

import com.pawd.pawdiumtools.init.ModItems;
import com.pawd.pawdiumtools.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabPD
{
    public static final CreativeTabs PD_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.pawdiumPickaxe;
        }
    };
}
