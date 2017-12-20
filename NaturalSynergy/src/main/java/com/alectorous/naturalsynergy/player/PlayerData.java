package com.alectorous.naturalsynergy.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PlayerData {
	
	private EnumClass playerClass;
	private int level; 	
	private boolean canSwitch;
	private BlockPos linkedPedestal;
	private World pedestalWorld;
	
	public void readFromPlayer(EntityPlayer player){
		NBTTagCompound tag = player.getEntityData();
		this.playerClass = stringToClass(tag.getString("class"));
		this.level = tag.getInteger("level");
		this.canSwitch = tag.getBoolean("canSwitch");
		
		if (!tag.getString("linkedPedestal").equals("none")) {
			String[] coords = tag.getString("linkedPedestal").split(":");
			this.linkedPedestal = new BlockPos(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
			for (World w : DimensionManager.getWorlds()) {
				if (w.provider.getDimension() == Integer.parseInt(coords[3])) {
					pedestalWorld = w;
				}
			}
		}
	}
	
	public void saveToPlayer(EntityPlayer player) {
		NBTTagCompound tag = player.getEntityData();
		tag.setString("class", classToString(playerClass));
		tag.setInteger("level", level);
		tag.setBoolean("canSwitch", canSwitch);
		if (this.linkedPedestal != null) {
			tag.setString("linkedPedestal", linkedPedestal.getX() + ":" + linkedPedestal.getY() + ":" + linkedPedestal.getZ() + ":" + pedestalWorld.provider.getDimension());
		}
		else {
			tag.setString("linkedPedestal", "none");
		}
	}
	
	public void loadDefaults() {
		this.playerClass = EnumClass.NONE;
		this.level = 0;
		this.canSwitch = false;
	}
	
	public EnumClass getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(EnumClass playerClass) {
		this.playerClass = playerClass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isCanSwitch() {
		return canSwitch;
	}

	public void setCanSwitch(boolean canSwitch) {
		this.canSwitch = canSwitch;
	}

	public BlockPos getLinkedPedestal() {
		return linkedPedestal;
	}

	public void setLinkedPedestal(BlockPos linkedPedestal) {
		this.linkedPedestal = linkedPedestal;
	}
	
	public static String classToString(EnumClass playerClass) {
		switch (playerClass){
		case WATER:
			return "water";
		case AIR:
			return "air";
		case EARTH:
			return "earth";
		case FIRE:
			return "fire";
		case NONE:
			return "none";
		}
		return null;
	}
	
	public static EnumClass stringToClass(String playerClass) {
		if (playerClass.equals("water")) {
			return EnumClass.WATER;
		}
		else if (playerClass.equals("air")) {
			return EnumClass.AIR;
		}
		else if (playerClass.equals("earth")) {
			return EnumClass.EARTH;
		}
		else if (playerClass.equals("fire")) {
			return EnumClass.FIRE;
		}
		else {
			return EnumClass.NONE;
		}
	}
}

