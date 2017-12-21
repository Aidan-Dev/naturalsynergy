package com.alectorous.naturalsynergy.client.gui;

import com.alectorous.naturalsynergy.player.EnumClass;
import com.alectorous.naturalsynergy.player.PlayerData;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGuiHandler {
	
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if (event.getType() != ElementType.EXPERIENCE) return;
		PlayerData data = new PlayerData();
		data.readFromPlayer(Minecraft.getMinecraft().player);
		if (data.availableClasses.keySet().size()>0 && !data.getPlayerClass().equals(EnumClass.NONE)) {
			new GuiHUD(Minecraft.getMinecraft());
		}
	}
	
}
