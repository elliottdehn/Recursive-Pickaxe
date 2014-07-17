package com.pawd.recursivepickaxe.creativetab;

import com.pawd.recursivepickaxe.init.ModItems;
import com.pawd.recursivepickaxe.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabRP
{
    public static final CreativeTabs RP_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.recursivePickaxe;
        }
    };
}
