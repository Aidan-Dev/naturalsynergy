package com.alectorous.naturalsynergy.world;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;

public class ElementalChunkGenerator implements IChunkGenerator {

	private final World world;
	private final IBlockState[] cachedBlockIDs = new IBlockState[256];

	public ElementalChunkGenerator(World world) {
		this.world = world;
		for (int i = 0; i < 256; i++) {
			cachedBlockIDs[i] = Blocks.AIR.getDefaultState();
			System.out.println(cachedBlockIDs[i]);
		}
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();

        for (int i = 0; i < this.cachedBlockIDs.length; ++i){
        	
            IBlockState iblockstate = this.cachedBlockIDs[i];
            if (iblockstate != null){
                for (int j = 0; j < 16; ++j){
                    for (int k = 0; k < 16; ++k){
                    	if (x==0 && z == 0 && j==0 && k==0 && i== 98) {
                    		chunkprimer.setBlockState(j, i, k, Blocks.STONE.getDefaultState());
                    	}
                    	else {	
                    		chunkprimer.setBlockState(j, i, k, iblockstate);
                    	}
                    }
                }
            }
        }

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
		Biome[] abiome = this.world.getBiomeProvider().getBiomes((Biome[]) null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int l = 0; l < abyte.length; ++l) {
			abyte[l] = (byte) Biome.getIdForBiome(abiome[l]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}

}
