package com.alectorous.naturalsynergy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePlayerData extends MessageBase<MessagePlayerData>{
	
	private NBTTagCompound tag;
	
	public void setData(NBTTagCompound tag) {
		this.tag = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public void handleClientSide(MessagePlayerData message, EntityPlayer player) {
		Minecraft.getMinecraft().player.getEntityData().merge(message.tag);
	}

	@Override
	public void handleServerSide(MessagePlayerData message, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
