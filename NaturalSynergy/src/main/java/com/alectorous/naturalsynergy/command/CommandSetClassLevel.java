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
import net.minecraft.util.text.TextComponentTranslation;

public class CommandSetClassLevel extends CommandBase{
	
	private ArrayList<String> aliases;
	
	public CommandSetClassLevel() {
		aliases = Lists.newArrayList(Reference.MOD_ID, "setClassLevel", "setclasslevel", "SETCLASSLEVEL");
	}	
	
	@Override
	public String getName() {
		return "setClassLevel";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "setClassLevel <int>";
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
					data.setClassLevel(level);
					data.saveToPlayer(player);
				}
				catch (NumberFormatException e) {
					player.sendMessage(new TextComponentTranslation("Please enter a number from 0 to 3"));
				}
			}
		}
		
	}
	
}
