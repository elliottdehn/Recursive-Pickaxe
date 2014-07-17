package com.pawd.recursivepickaxe.item;

import com.pawd.recursivepickaxe.creativetab.CreativeTabRP;
import com.pawd.recursivepickaxe.reference.BlockReference;
import com.pawd.recursivepickaxe.utility.LogHelper;
import com.pawd.recursivepickaxe.utility.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRecursivePickaxe extends ItemRPTool
{

    public ItemRecursivePickaxe()
    {
        super(2, ToolMaterial.EMERALD, BlockReference.BLOCKS_PICKAXE);
        this.setUnlocalizedName("recursivePickaxe");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        NBTHelper.setBoolean(stack, "mining", false);
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack)
    {
/* ========= MEKANISM STYLE ==========*/
/*      int hardness = 0;
        if(block == Blocks.obsidian)
        {
            hardness = 3;
        }
        if(block == Blocks.diamond_block || block == Blocks.diamond_ore
           || block == Blocks.gold_block || block == Blocks.gold_ore
           || block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore)
        {
            hardness = 2;
        }
        if(block == Blocks.iron_block || block == Blocks.iron_ore
           || block == Blocks.lapis_block || block == Blocks.lapis_ore)
        {
            hardness = 1;
        }
        if(hardness != 0)
        {
            return toolMaterial.getHarvestLevel() >= hardness;
        }
        else
        {
            if(block.getMaterial() == Material.rock) return true;
        }

        return block.getMaterial() == Material.iron; */

/* ====== SIMPLE, HARDCODED STYLE ====== */
        if(block.getMaterial() == Material.rock) return true;
        return block.getMaterial() == Material.iron;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        if(player instanceof EntityPlayerMP)
        {
            World world = player.worldObj;
            Block block = world.getBlock(x,y,z);
            EntityPlayerMP mplayer = (EntityPlayerMP) player;

            if(block == Blocks.iron_ore)
            {
                if(!NBTHelper.getBoolean(stack,"mining"))
                {
                    NBTHelper.setBoolean(stack, "mining", true);
                    mplayer.theItemInWorldManager.tryHarvestBlock(x, y, z);
                }
                NBTHelper.setBoolean(stack, "mining", false);
                return false;
            }
        }
        return super.onBlockStartBreak(stack,x,y,z,player);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if(block.getMaterial() == Material.rock) return 20.0f;
        if(block.getMaterial() == Material.iron) return 20.0f;
        return 1.0f;
    }
}
