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

public class CommandRemoveClass extends CommandBase{
	
	private ArrayList aliases;
	
	public CommandRemoveClass() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "removeClass", "removeclass", "REMOVECLASS");
	}
	
	@Override
	public String getName() {
		return "removeClass";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "removeClass <class>";
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
			data.removeClass(PlayerData.stringToClass(args[0]));
			data.saveToPlayer(player);
		}
		
	}
		
	
	
}
