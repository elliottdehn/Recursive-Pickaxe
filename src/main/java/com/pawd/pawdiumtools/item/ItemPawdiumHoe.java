package com.pawd.pawdiumtools.item;

import com.pawd.pawdiumtools.reference.BlockReference;
import com.pawd.pawdiumtools.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemPawdiumHoe extends ItemPD
{
    public ItemPawdiumHoe()
    {
        super();
        this.setUnlocalizedName("pawdiumHoe");
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float what, float what2,float what3)
    {
        if(!player.canPlayerEdit(x,y,z,meta,stack)) return false;
        else
        {
            UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
            if (MinecraftForge.EVENT_BUS.post(event))
            {
                return false;
            }
            tillSoil(stack,player,world,x,y,z,meta);
            return true;
        }
    }

    private void tillSoil(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta)
    {
        for(int xPos = x - 1; xPos <= x + 1; xPos++)
        {
            for(int zPos = z - 1; zPos <= z + 1; zPos++)
            {
                if(world.getBlock(xPos,y,zPos) == Blocks.grass || world.getBlock(xPos,y,zPos) == Blocks.dirt)
                {
                    stack.damageItem(1,player);
                    world.setBlock(xPos,y,zPos,Blocks.farmland);
                    world.playSoundEffect((double)((float)x + 0.5F),
                            (double)((float)y + 0.5F),
                            (double)((float)z + 0.5F),
                            world.getBlock(x,y,z).stepSound.getStepResourcePath(),
                            (world.getBlock(x,y,z).stepSound.getVolume() + 1.0F) / 2.0F,
                            world.getBlock(x,y,z).stepSound.getPitch() * 0.8F);
                }
            }
        }
    }
}
