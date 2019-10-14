package com.example.examplemod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.example.examplemod.commands.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
 
@Mod(modid="soviamod", name="SkyNet", version="1.1.0")


public class ExampleMod {
	
	Minecraft mc = Minecraft.getMinecraft();
    boolean isEnabled = true;
    boolean chestPopper = false;
    KeyBinding toggle;
    public String manaGroup;
    public String testingString;
    KeyBinding toggle2;
    List<String> previousPlayerList = new ArrayList();
    public static List<String> enemies = new ArrayList();   
    public String manaAmountStr;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)  {
        MinecraftForge.EVENT_BUS.register(this);
        toggle = new KeyBinding("Toggle SkyNet", Keyboard.KEY_I, "SkyNet");
        toggle2 = new KeyBinding("Claim Mana", Keyboard.KEY_APOSTROPHE, "SkyNet");
        ClientRegistry.registerKeyBinding(toggle);    
        ClientRegistry.registerKeyBinding(toggle2);
		ClientCommandHandler.instance.registerCommand(new SetManaGroup());
		ClientCommandHandler.instance.registerCommand(new SetJoinMessage());
		
		if(!ConfigHandler.hasKey("Strings", "ManaGroup")) {
			ConfigHandler.writeConfig("Strings", "ManaGroup", "NSMana");
		}
		
		if(!ConfigHandler.hasCategory("SkyNet")) {
			
		}
    }
    
    public static void serverLoad(FMLServerStartingEvent event)
    {
    }
   
    
    public String filterChatColors(String s) {
        return TextFormatting.getTextWithoutFormattingCodes(s);
    }
   
    public void onPlayerLeave(String player) {
    	 if(!player.contains("~BTLP")) {
    		 mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SkyNet] "+ TextFormatting.GRAY + player + " left the game")); 
    	 }
    }
    
 
    public void onPlayerJoin(String player) {
    if(!player.contains("~BTLP")) {
    	if(ConfigHandler.hasKey("Strings", player)) {
    		mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SkyNet] "+ TextFormatting.YELLOW + player + " has arrived. "+ConfigHandler.getString("Strings", player)));
    	} else {
    		mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SkyNet] "+ TextFormatting.GRAY + player + " joined the game" ));
    	}
    }
}
 
    @SubscribeEvent
    public void onTick(ClientTickEvent event) {
        if(event.phase == TickEvent.Phase.START) {
            if(isEnabled) {
                if(mc.world != null) {      
                ArrayList<String> playerList = new ArrayList();
                Collection<NetworkPlayerInfo> players = mc.getConnection().getPlayerInfoMap();
                for(NetworkPlayerInfo info : players) {
                    playerList.add(filterChatColors(info.getGameProfile().getName()));
                }
                ArrayList<String> temp = (ArrayList)playerList.clone();
                playerList.removeAll(previousPlayerList);
                previousPlayerList.removeAll(temp);
                for(String player : previousPlayerList) {
                    onPlayerLeave(player);
                }
                for(String player : playerList) {
                    onPlayerJoin(player);
                }
                previousPlayerList = temp;
                }
            }
        }
        
    }
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
       event.getMessage();
    }
    
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event)
    {

    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(toggle.isPressed()){
            if(!isEnabled){
	            mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SkyNet] "+ TextFormatting.GRAY + "SkyNet+ Enabled"));
	            isEnabled = true;
            }else if(isEnabled){
	            mc.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE + "[SkyNet] "+ TextFormatting.GRAY + "SkyNet+ Disabled"));
	            isEnabled = false;
            }
        }        
        
        if(toggle2.isPressed()) {
        	mc.player.sendChatMessage("/manaclaim");
           	mc.player.sendChatMessage("/manatransfer "+ConfigHandler.getString("Strings", "ManaGroup")+" "+"all");
        }
  
    }   
}
