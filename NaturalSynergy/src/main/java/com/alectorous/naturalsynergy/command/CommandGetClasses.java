package com.alectorous.naturalsynergy.command;

import java.util.ArrayList;
import java.util.List;

import com.alectorous.naturalsynergy.main.Reference;
import com.alectorous.naturalsynergy.player.PlayerData;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandGetClasses extends CommandBase{
	private ArrayList aliases;
	
	public CommandGetClasses() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "getClasses", "getclasses", "GETCLASSES");
	}
	
	@Override
	public String getName() {
		return "getClasses";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "getClasses";
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			
			PlayerData data = new PlayerData();
			data.readFromPlayer(player);
			
			player.sendMessage(new TextComponentTranslation(data.availableClasses.toString()));
		}
	}
		
}
