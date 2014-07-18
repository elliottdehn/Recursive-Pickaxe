package com.pawd.pawdiumtools.item;

import com.pawd.pawdiumtools.reference.BlockReference;
import com.pawd.pawdiumtools.utility.LogHelper;
import com.pawd.pawdiumtools.utility.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPawdiumPickaxe extends ItemPDTool
{

    public ItemPawdiumPickaxe()
    {
        super(2, ToolMaterial.EMERALD, BlockReference.BLOCKS_PICKAXE);
        this.setUnlocalizedName("pawdiumPickaxe");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        NBTHelper.setBoolean(stack, "mining", false);
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack)
    {
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

            if(block.getUnlocalizedName().toLowerCase().contains("ore"))
            {
                if(!NBTHelper.getBoolean(stack,"mining"))
                {
                    NBTHelper.setBoolean(stack, "mining", true);
                    mineVein(block,x,y,z,world.getBlockMetadata(x,y,z),world,mplayer);
                }
                NBTHelper.setBoolean(stack, "mining", false);
                return false;
            }
        }
        return super.onBlockStartBreak(stack,x,y,z,player);
    }

    public void mineVein(Block block, int x, int y, int z, int meta, World world, EntityPlayerMP mplayer)
    {
        for(int x2 = x - 1; x2 <= x + 1; x2++)
        {
            for(int y2 = y - 1; y2 <= y + 1; y2++)
            {
                for(int z2 = z - 1; z2 <= z + 1; z2++)
                {
                    Block block2 = world.getBlock(x2,y2,z2);
                    if(block2 == block & world.getBlockMetadata(x2,y2,z2) == meta)
                    {
                        mplayer.theItemInWorldManager.tryHarvestBlock(x2,y2,z2);
                        mineVein(block,x2,y2,z2,meta,world,mplayer);
                    }
                }
            }
        }
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if(block.getMaterial() == Material.rock) return 20.0f;
        if(block.getMaterial() == Material.iron) return 20.0f;
        return 1.0f;
    }
}
