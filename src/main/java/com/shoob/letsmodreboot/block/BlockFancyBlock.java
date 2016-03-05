package com.shoob.letsmodreboot.block;

import com.shoob.letsmodreboot.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockFancyBlock extends BlockLMR{

    public BlockFancyBlock() {
        super();
        this.setUnlocalizedName("fancyBlock");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        Block newBlock = ModBlocks.fancyBlock;
        IBlockState newState = newBlock.getDefaultState();
        BlockPos newPos = pos.add(0,1,0);
        if(worldIn.isAirBlock(newPos))
            worldIn.setBlockState(newPos, newState);
        worldIn.playSoundEffect(pos.getX(),pos.getY(), pos.getZ(), "tile.piston.out", 1.0F, 1.0F);
        return true;
    }
}
