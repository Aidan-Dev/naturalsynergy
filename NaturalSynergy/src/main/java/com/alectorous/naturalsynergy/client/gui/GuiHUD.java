package com.alectorous.naturalsynergy.client.gui;

import com.alectorous.naturalsynergy.player.PlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiHUD extends Gui{
	
	
	public GuiHUD(Minecraft mc) {
		
		ScaledResolution scaled = new ScaledResolution(mc);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();

		PlayerData data = new PlayerData();
		data.readFromPlayer(Minecraft.getMinecraft().player);
		String playerClass = "Class: " + PlayerData.classToString(data.getPlayerClass());
		String level = "Level: " + data.getLevel(); 
		
		drawString(mc.fontRenderer, playerClass, 10, 10, Integer.parseInt("00FFFF", 16));
		drawString(mc.fontRenderer, level, 10, 20, Integer.parseInt("00FFFF", 16));
	}
	
}
