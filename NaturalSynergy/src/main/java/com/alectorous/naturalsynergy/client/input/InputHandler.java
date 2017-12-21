package com.alectorous.naturalsynergy.client.input;

import org.lwjgl.input.Keyboard;

import com.alectorous.naturalsynergy.player.PlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@Mod.EventBusSubscriber
public class InputHandler {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.cycleKey.isPressed()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
				
			}
			else {
				PlayerData data = new PlayerData();
				data.readFromPlayer(Minecraft.getMinecraft().player);
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(data.getClass() + " " + data.getLevel()));
			}
		}
	}
}
