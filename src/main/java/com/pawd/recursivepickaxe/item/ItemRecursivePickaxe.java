package com.pawd.recursivepickaxe.item;

import com.pawd.recursivepickaxe.creativetab.CreativeTabRP;

public class ItemRecursivePickaxe extends ItemRP
{
    public ItemRecursivePickaxe()
    {
        super();
        this.setUnlocalizedName("recursivePickaxe");
        this.setCreativeTab(CreativeTabRP.RP_TAB);
        this.setMaxStackSize(1);
        this.setHarvestLevel("pickaxe", 2);
    }
}
