package com.alectorous.naturalsynergy.command;

import java.util.ArrayList;
import java.util.List;

import com.alectorous.naturalsynergy.main.Reference;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandGetLevel extends CommandBase{
	
private ArrayList aliases;
	
	public CommandGetLevel() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "getLevel", "getlevel", "GETLEVEL");
	}
	
	@Override
	public String getName() {
		return "getLevel";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "getLevel";
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			if (player.getEntityData().hasKey("level")) {
				
				player.sendMessage(new TextComponentTranslation(Integer.toString(player.getEntityData().getInteger("level"))));
			}
			else {
				player.sendMessage(new TextComponentTranslation("You haven't set a level yet"));
			}
		}
		
	}
		
	
}
