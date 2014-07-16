package com.pawd.recursivepickaxe.creativetab;

import com.pawd.recursivepickaxe.init.ModItems;
import com.pawd.recursivepickaxe.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabRC
{
    public static final CreativeTabs RC_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.recursivePickaxe;
        }
        @Override
        public String getTranslatedTabLabel()
        {
            return Reference.MOD_NAME;
        }
    };
}
