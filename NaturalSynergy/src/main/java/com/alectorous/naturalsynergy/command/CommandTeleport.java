package com.alectorous.naturalsynergy.command;

import java.util.ArrayList;
import java.util.List;

import com.alectorous.naturalsynergy.main.Reference;
import com.alectorous.naturalsynergy.world.DimensionalTeleporter;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandTeleport extends CommandBase{

	private ArrayList<String> aliases;
	
	public CommandTeleport() {
		aliases = Lists.newArrayList(Reference.MOD_ID, "teleport", "TELEPORT");
	}

	@Override
	public String getName() {
		return "teleport";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "teleport <id>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) sender;
			
			if (args.length == 1) {
				try {
					int dimension  = Integer.parseInt(args[0]);
					DimensionalTeleporter.teleportToDimension(player, dimension, 0.5, 100, 0.5);
					
				}
				catch (NumberFormatException e) {
					player.sendMessage(new TextComponentTranslation("Please enter a legitimate id"));
				}
			}
		}
		
	}
		
}
