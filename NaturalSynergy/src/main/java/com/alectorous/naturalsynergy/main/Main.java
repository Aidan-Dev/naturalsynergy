package com.alectorous.naturalsynergy.main;

import com.alectorous.naturalsynergy.command.CommandGetClass;
import com.alectorous.naturalsynergy.command.CommandGetLevel;
import com.alectorous.naturalsynergy.command.CommandSetClass;
import com.alectorous.naturalsynergy.command.CommandSetLevel;
import com.alectorous.naturalsynergy.command.CommandTeleport;
import com.alectorous.naturalsynergy.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class Main{
    
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide=Reference.CLIENT, serverSide=Reference.COMMON)
	public static CommonProxy proxy;
	
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandGetClass());
		event.registerServerCommand(new CommandSetClass());
		event.registerServerCommand(new CommandGetLevel());
		event.registerServerCommand(new CommandSetLevel());
		event.registerServerCommand(new CommandTeleport());
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		DimensionRegistry.init();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event){
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
    
}
