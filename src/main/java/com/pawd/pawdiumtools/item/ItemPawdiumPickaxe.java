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

import java.util.*;

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
        NBTHelper.setStringList(stack,"ores",new ArrayList<String>());
        NBTHelper.setBoolean(stack,"manual",true);
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
        ArrayList<String> list;
        list = NBTHelper.getStringList(stack,"ores");

        if(player instanceof EntityPlayerMP)
        {
            World world = player.worldObj;
            Block block = world.getBlock(x,y,z);
            int meta = world.getBlockMetadata(x,y,z);
            EntityPlayerMP mplayer = (EntityPlayerMP) player;

            if(block.getUnlocalizedName().toLowerCase().contains("ore"))
            {
                if(NBTHelper.getBoolean(stack,"manual"))
                {
                    if(!list.isEmpty())
                    {
                        if (list.get(0).equals(x + "," + y + "," + z))
                        {
                            if (list.size() == 1)
                            {
                                list.remove(0);
                                NBTHelper.setStringList(stack, "ores", list);
                                return false;
                            } else
                            {
                                String[] coords = list.get(list.size() - 1).split(",");
                                int xPos = Integer.parseInt(coords[0]);
                                int yPos = Integer.parseInt(coords[1]);
                                int zPos = Integer.parseInt(coords[2]);
                                Block block2 = world.getBlock(xPos,yPos,zPos);
                                int meta2 = world.getBlockMetadata(xPos,yPos,zPos);
                                if(!(block2 == block && meta2 == meta))
                                {
                                    if(!(block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore))
                                    {
                                        list.remove(list.size() - 1);
                                        NBTHelper.setStringList(stack, "ores", list);
                                        mplayer.theItemInWorldManager.tryHarvestBlock(x, y, z);
                                        return true;
                                    }
                                    else
                                    {
                                        if(!(block2 == Blocks.redstone_ore || block2 == Blocks.lit_redstone_ore))
                                        {
                                            list.remove(list.size() - 1);
                                            NBTHelper.setStringList(stack, "ores", list);
                                            mplayer.theItemInWorldManager.tryHarvestBlock(x, y, z);
                                            return true;
                                        }
                                    }
                                }
                                NBTHelper.setBoolean(stack, "manual", false);
                                mplayer.theItemInWorldManager.tryHarvestBlock(xPos, yPos, zPos);
                                world.playAuxSFXAtEntity(null, 2001, xPos, yPos, zPos, Block.getIdFromBlock(block) + (world.getBlockMetadata(xPos, yPos, zPos) << 12));
                                list.remove(list.size() - 1);
                                NBTHelper.setStringList(stack, "ores", list);
                                return true;
                            }
                        }
                        else list = new ArrayList<String>();
                    }

                    if(list.isEmpty())
                    {
                        list.add(x + "," + y + "," + z);
                        list = findOres(block, x, y, z, world.getBlockMetadata(x, y, z), world, mplayer, list);
                        NBTHelper.setStringList(stack, "ores", list);
                        mplayer.theItemInWorldManager.tryHarvestBlock(x,y,z);
                        return true;
                    }
                }
            }
        }
        NBTHelper.setBoolean(stack,"manual",true);
        return super.onBlockStartBreak(stack,x,y,z,player);
    }

    private ArrayList<String> findOres(Block block, int x, int y, int z, int meta, World world, EntityPlayerMP mplayer, ArrayList list)
    {

        for(int xPos = x - 1; xPos <= x + 1; xPos++)
        {
            for(int yPos = y - 1; yPos <= y + 1; yPos++)
            {
                for(int zPos = z - 1; zPos <= z + 1; zPos++)
                {
                    Block block2 = world.getBlock(xPos,yPos,zPos);
                    int meta2 = world.getBlockMetadata(xPos,yPos,zPos);
                    String coords = xPos + "," + yPos + "," + zPos;

                    if ((block2 == block && meta2 == meta) || ((block2 == Blocks.lit_redstone_ore || block2 == Blocks.redstone_ore) && (block == Blocks.lit_redstone_ore || block == Blocks.redstone_ore)))
                    {
                        if (!list.contains(coords))
                        {
                            list.add(coords);
                            list = findOres(block, xPos, yPos, zPos, meta, world, mplayer, list);
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if(block.getMaterial() == Material.rock) return 20.0f;
        if(block.getMaterial() == Material.iron) return 20.0f;
        return 1.0f;
    }
}
