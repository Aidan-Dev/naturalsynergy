package com.alectorous.naturalsynergy.proxy;

import com.alectorous.naturalsynergy.client.gui.RenderGuiHandler;
import com.alectorous.naturalsynergy.client.input.InputHandler;
import com.alectorous.naturalsynergy.client.input.KeyBindings;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void init() {
		super.init();
		
		MinecraftForge.EVENT_BUS.register(new InputHandler());
		MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
		KeyBindings.init();
	}
	
}
