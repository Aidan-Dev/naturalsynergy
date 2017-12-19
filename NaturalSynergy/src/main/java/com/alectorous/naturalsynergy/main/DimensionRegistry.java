package com.alectorous.naturalsynergy.main;

import com.alectorous.naturalsynergy.world.ElementalWorldProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
	
	public static DimensionType elementalDimensionType;
	
	public static void init() {
		registerDimensionTypes();
		registerDimensions();
		
	}
	
	private static void registerDimensionTypes() {
		elementalDimensionType = DimensionType.register(Reference.MOD_ID, "_dimension", 7, ElementalWorldProvider.class, false);
	}
	
	private static void registerDimensions(){
		DimensionManager.registerDimension(7, elementalDimensionType);
	}
	
}
