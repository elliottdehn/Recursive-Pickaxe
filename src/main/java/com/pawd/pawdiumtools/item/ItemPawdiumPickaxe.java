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

import java.util.Timer;
import java.util.TimerTask;

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

    private void mineVein(Block block, int x, int y, int z, int meta, World world, EntityPlayerMP mplayer)
    {
        Block[] blocks = new Block[6];
        int[] metas = new int[6];

        mplayer.theItemInWorldManager.tryHarvestBlock(x,y,z);

        blocks[0] = world.getBlock(x+1,y,z); metas[0] = world.getBlockMetadata(x+1,y,z);
        blocks[1] = world.getBlock(x-1,y,z); metas[1] = world.getBlockMetadata(x-1,y,z);
        blocks[2] = world.getBlock(x,y+1,z); metas[2] = world.getBlockMetadata(x,y+1,z);
        blocks[3] = world.getBlock(x,y-1,z); metas[3] = world.getBlockMetadata(x,y-1,z);
        blocks[4] = world.getBlock(x,y,z+1); metas[4] = world.getBlockMetadata(x,y,z+1);
        blocks[5] = world.getBlock(x,y,z-1); metas[5] = world.getBlockMetadata(x,y,z-1);

        for(int j = 0; j <= 5; j++)
        {
            if(blocks[j] == block && metas[j] == meta)
            {
                switch (j)
                {
                    case 0:
                        delayMine(block,x+1,y,z,meta,world,mplayer);
                        break;
                    case 1:
                        delayMine(block,x-1,y,z,meta,world,mplayer);
                        break;
                    case 2:
                        delayMine(block,x,y+1,z,meta,world,mplayer);
                        break;
                    case 3:
                        delayMine(block,x,y-1,z,meta,world,mplayer);
                        break;
                    case 4:
                        delayMine(block,x,y,z+1,meta,world,mplayer);
                        break;
                    case 5:
                        delayMine(block,x,y,z-1,meta,world,mplayer);
                        break;
                }
            }
        }
    }

    private void delayMine(Block block, int x, int y, int z, int meta, World world, EntityPlayerMP mplayer)
    {
        final Block fblock = block;
        final int fx = x;
        final int fy = y;
        final int fz = z;
        final int fmeta = meta;
        final World fworld = world;
        final EntityPlayerMP fplayer = mplayer;

        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                mineVein(fblock,fx,fy,fz,fmeta,fworld,fplayer);
            }
        }, 200);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if(block.getMaterial() == Material.rock) return 20.0f;
        if(block.getMaterial() == Material.iron) return 20.0f;
        return 1.0f;
    }
}
