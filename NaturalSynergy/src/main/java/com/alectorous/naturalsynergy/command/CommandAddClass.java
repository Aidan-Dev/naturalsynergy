package com.alectorous.naturalsynergy.command;

import java.util.ArrayList;
import java.util.List;

import com.alectorous.naturalsynergy.main.Reference;
import com.alectorous.naturalsynergy.network.MessagePlayerData;
import com.alectorous.naturalsynergy.network.PacketHandler;
import com.alectorous.naturalsynergy.player.PlayerData;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandAddClass extends CommandBase{
	
	private ArrayList aliases;
	
	public CommandAddClass() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "addClass", "addclass", "ADDCLASS");
	}
	
	@Override
	public String getName() {
		return "addClass";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "addClass <class>";
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			return;
		}
		
		String s = args[0];
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			PlayerData data = new PlayerData();
			data.readFromPlayer(player);
			data.addClass(PlayerData.stringToClass(args[0]));
			data.saveToPlayer(player);
		}
		
	}
		
	
	
}
