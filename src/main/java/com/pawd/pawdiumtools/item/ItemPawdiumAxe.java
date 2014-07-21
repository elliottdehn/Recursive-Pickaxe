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

public class ItemPawdiumAxe extends ItemPDTool
{
    public ItemPawdiumAxe()
    {
        super(2, ToolMaterial.EMERALD, BlockReference.BLOCKS_AXE);
        this.setUnlocalizedName("pawdiumAxe");
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        NBTHelper.setBoolean(stack, "chopping", false);
    }

    @Override
    public boolean canHarvestBlock(Block block, ItemStack stack)
    {
        return block.getMaterial() == Material.wood;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        if(player instanceof EntityPlayerMP)
        {
            World world = player.worldObj;
            Block block = world.getBlock(x,y,z);
            Block block2 = world.getBlock(x,y+1,z);
            Boolean stump = false;
            EntityPlayerMP mplayer = (EntityPlayerMP) player;

            if(block.getUnlocalizedName().toLowerCase().contains("log"))
            {
                if(!NBTHelper.getBoolean(stack, "chopping"))
                {
                    NBTHelper.setBoolean(stack, "chopping", true);
                    int yPos = y + 1;
                    while (world.getBlock(x, yPos, z).getMaterial() == Material.wood)
                    {
                        yPos++;
                    }
                    yPos--;
                    if(block2.getMaterial() == Material.wood) stump = true; else stump = false;
                    mplayer.theItemInWorldManager.tryHarvestBlock(x, yPos, z);
                    world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(block) + (world.getBlockMetadata(x,y,z) << 12));
                }
                NBTHelper.setBoolean(stack, "chopping", false);
                return stump;
            }
        }
        return super.onBlockStartBreak(stack,x,y,z,player);
    }
}
