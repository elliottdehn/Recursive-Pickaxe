package com.pawd.recursivepickaxe.item;

import com.pawd.recursivepickaxe.creativetab.CreativeTabRP;
import com.pawd.recursivepickaxe.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Arrays;
import java.util.HashSet;

public class ItemRPTool extends ItemTool
{
    public ItemRPTool(int mobBoost, ToolMaterial toolMaterial, Block[] effectiveBlocks)
    {
        super(mobBoost, toolMaterial, new HashSet<Block>(Arrays.asList(effectiveBlocks)));
        this.setCreativeTab(CreativeTabRP.RP_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }


    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
