package com.example.examplemod.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import com.example.examplemod.*;

public class SetJoinMessage extends CommandBase {
	
	Minecraft mc = Minecraft.getMinecraft();
	public String message;
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setjoinmessage";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "Frames an enemy ";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		//Builds String
		StringBuilder strBuilder = new StringBuilder();
	    for (int i = 0; i < args.length; i++) {
	      strBuilder.append(args[i] + " ");
	    }
	    message = strBuilder.toString();
		ConfigHandler.writeConfig("Strings", args[0], message.substring(args[0].length()+1));
		mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SoviaMod] "+ TextFormatting.GRAY + "Set " + args[0] + " join message to: "+ConfigHandler.getString("Strings", args[0])));	
	}
	
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}