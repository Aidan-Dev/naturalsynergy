package com.alectorous.naturalsynergy.network;

import com.alectorous.naturalsynergy.main.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		
		//Register Packets
		INSTANCE.registerMessage(MessagePlayerData.class, MessagePlayerData.class, 0, Side.CLIENT);
		
	}
	
	public static void sendToClient(IMessage message, EntityPlayer player) {
		INSTANCE.sendTo(message, (EntityPlayerMP)player);
	}
	
}
