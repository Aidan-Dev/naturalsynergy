package com.alectorous.naturalsynergy.command;

import java.util.ArrayList;
import java.util.List;

import com.alectorous.naturalsynergy.main.Reference;
import com.alectorous.naturalsynergy.world.ElementalWorldProvider;
import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.DimensionManager;

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
		
		String message = "NOTHING";
		for (World w : DimensionManager.getWorlds()) {
			if (w.provider instanceof WorldProviderHell) {
				message = w.getBlockState(new BlockPos(0, 98, 0)).getBlock().getUnlocalizedName();
			}
				
		}
		
		player.sendMessage(new TextComponentString(TextFormatting.BLUE + message));
		
		
	}
		
}
