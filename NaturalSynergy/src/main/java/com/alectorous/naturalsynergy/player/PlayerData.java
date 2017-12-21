package com.alectorous.naturalsynergy.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.alectorous.naturalsynergy.network.MessagePlayerData;
import com.alectorous.naturalsynergy.network.PacketHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PlayerData {
	
	private EnumClass activeClass;
	public HashMap<EnumClass, Integer> availableClasses = new HashMap<>();
	private int activeLevel; 
	private BlockPos linkedPedestal;
	private World pedestalWorld;
	
	
	public void readFromPlayer(EntityPlayer player){
		NBTTagCompound tag = player.getEntityData();
		this.activeClass = stringToClass(tag.getString("class"));
		this.activeLevel = tag.getInteger("level");
		
		if (tag.hasKey("availableClasses")) {
			String[] sets = tag.getString("availableClasses").split("%");
			
			for (String s : sets) {
				String[] halves = s.split(":");
				this.availableClasses.put(stringToClass(halves[0]), Integer.parseInt(halves[1]));
			}
		}
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
	
	public void readFromNBT(NBTTagCompound tag){
		this.activeClass = stringToClass(tag.getString("class"));
		this.activeLevel = tag.getInteger("level");
		
		if (tag.hasKey("availableClasses")) {
			String[] sets = tag.getString("availableClasses").split("%");
			
			for (String s : sets) {
				String[] halves = s.split(":");
				this.availableClasses.put(stringToClass(halves[0]), Integer.parseInt(halves[1]));
			}
		}
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
		tag.setString("class", classToString(activeClass));
		tag.setInteger("level", activeLevel);
		if (this.linkedPedestal != null) {
			tag.setString("linkedPedestal", linkedPedestal.getX() + ":" + linkedPedestal.getY() + ":" + linkedPedestal.getZ() + ":" + pedestalWorld.provider.getDimension());
		}
		else {
			tag.setString("linkedPedestal", "none");
		}
		
		if (this.availableClasses.size()>0) {
			StringBuilder s = new StringBuilder("");
			for (EnumClass playerClass : this.availableClasses.keySet()) {
				s.append(classToString(playerClass));
				s.append(":");
				s.append(this.availableClasses.get(playerClass));
				s.append("%");
			}
			tag.setString("availableClasses", s.toString());
		}
		
		syncClient(player);
		
	}
	
	
	public void syncClient(EntityPlayer player) {
		MessagePlayerData message = new MessagePlayerData();
		message.setData(player.getEntityData());
		PacketHandler.sendToClient(message, player);
	}
	
	public NBTTagCompound toNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("class", classToString(activeClass));
		tag.setInteger("level", activeLevel);
		if (this.linkedPedestal != null) {
			tag.setString("linkedPedestal", linkedPedestal.getX() + ":" + linkedPedestal.getY() + ":" + linkedPedestal.getZ() + ":" + pedestalWorld.provider.getDimension());
		}
		else {
			tag.setString("linkedPedestal", "none");
		}
		
		if (this.availableClasses.size()>0) {
			StringBuilder s = new StringBuilder("");
			for (EnumClass playerClass : this.availableClasses.keySet()) {
				s.append(classToString(playerClass));
				s.append(":");
				s.append(this.availableClasses.get(playerClass));
				s.append("%");
			}
			tag.setString("availableClasses", s.toString());
		}
		return tag;
	}
	
	public void loadDefaults() {
		this.activeClass = EnumClass.NONE;
		this.activeLevel = 0;
	}
	
	public EnumClass getPlayerClass() {
		return activeClass;
	}

	public void setPlayerClass(EnumClass playerClass) {
		if (this.availableClasses.keySet().contains((playerClass))) {
			this.activeClass = playerClass;
		}
	}

	public int getLevel() {
		return activeLevel;
	}

	public void setClassLevel(int level) {
		if (this.activeClass != EnumClass.NONE) {
			this.availableClasses.put(activeClass, level);
		}
	}
	
	public void setActiveLevel(int level) {
		if (this.activeClass != EnumClass.NONE) {
			if (level <= this.availableClasses.get(activeClass)) {
				this.activeLevel = level;
			}
		}
	}


	public BlockPos getLinkedPedestal() {
		return linkedPedestal;
	}

	public void setLinkedPedestal(BlockPos linkedPedestal) {
		this.linkedPedestal = linkedPedestal;
	}
		
	public boolean canSwitch() {
		return this.availableClasses.size()>1;
	}
	
	public boolean hasClass(EnumClass playerClass) {
		return this.availableClasses.keySet().contains(playerClass);
	}
	
	public void addClass(EnumClass playerClass) {
		if (!this.availableClasses.keySet().contains(playerClass)) {
				this.availableClasses.put(playerClass, 0);
		}
	}
	
	public void removeClass(EnumClass playerClass) {
		this.availableClasses.remove(playerClass);
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

