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

public class CommandSetLevel extends CommandBase{
	
	private ArrayList<String> aliases;
	
	public CommandSetLevel() {
		aliases = Lists.newArrayList(Reference.MOD_ID, "setLevel", "setlevel", "SETLEVEL");
	}

	@Override
	public String getName() {
		return "setLevel";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "setLevel <int>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) sender;
			PlayerData data = new PlayerData();
			data.readFromPlayer(player);
			
			if (args.length == 1) {
				try {
					int level = Integer.parseInt(args[0]);
					data.setLevel(level);
					data.saveToPlayer(player);
				}
				catch (NumberFormatException e) {
					player.sendMessage(new TextComponentTranslation("Please enter a number from 0 to 3"));
				}
			}
		}
		
	}
	
}
