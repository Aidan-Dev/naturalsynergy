package com.alectorous.naturalsynergy.client.input;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {
	
	public static KeyBinding cycleKey;
	
	public static void init() {
		cycleKey = new KeyBinding("key.cycleKey", Keyboard.KEY_G, "key.categories.naturalsynergy");
		ClientRegistry.registerKeyBinding(cycleKey);
	}
	
}
