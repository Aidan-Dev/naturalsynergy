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

public class CommandGetClass extends CommandBase{
	private ArrayList aliases;
	
	public CommandGetClass() {
		
		aliases = Lists.newArrayList(Reference.MOD_ID, "getClass", "getclass", "GETCLASS");
	}
	
	@Override
	public String getName() {
		return "getClass";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "getClass";
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			if (player.getEntityData().hasKey("class")) {
				
				player.sendMessage(new TextComponentTranslation(player.getEntityData().getString("class")));
			}
			else {
				player.sendMessage(new TextComponentTranslation("You haven't set a class yet"));
			}
		}
		
	}
		
}
