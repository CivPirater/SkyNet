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

public class SetManaGroup extends CommandBase {
	
	Minecraft mc = Minecraft.getMinecraft();
	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setmanagroup";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/enemy";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		ConfigHandler.writeConfig("Strings", "ManaGroup", args[0]);
		mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SoviaMod] "+ TextFormatting.GRAY + "Mana Group Set: " + args[0]));
		System.out.println(ConfigHandler.getString("Strings", "ManaGroup"));

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