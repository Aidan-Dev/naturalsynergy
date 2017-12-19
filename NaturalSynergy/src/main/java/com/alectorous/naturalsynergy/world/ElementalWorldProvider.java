package com.alectorous.naturalsynergy.world;

import com.alectorous.naturalsynergy.main.DimensionRegistry;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class ElementalWorldProvider extends WorldProvider{

	@Override
	public DimensionType getDimensionType() {
		return DimensionRegistry.elementalDimensionType;
	}
	
	@Override
	public String getSaveFolder() {
		return "ElementalDimension";
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ElementalChunkGenerator(world);
	}
		
}
