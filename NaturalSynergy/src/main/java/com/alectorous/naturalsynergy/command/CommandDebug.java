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
import net.minecraft.util.text.TextComponentString;

public class CommandDebug extends CommandBase{
	private ArrayList aliases;
	
	public CommandDebug() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "test", "TEST");
	}
	
	@Override
	public String getName() {
		return "test";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "test";
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		EntityPlayer player = (EntityPlayer) sender;
		PlayerData data = new PlayerData();
		data.readFromPlayer(player);
		player.sendMessage(new TextComponentString(Integer.toString(data.availableClasses.size())));
		
	}
		
}
