
package com.github.InspiredOne.InspiredNations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiMap;

public class InspiredNations extends JavaPlugin {

	public Logger logger = Logger.getLogger("Minecraft"); // Variable to communicate with console
	private StartStop SS = new StartStop(this); // Deals with start-up and shut-down
	public MultiMap<Class<? extends InspiredGov>, InspiredGov> regiondata = new MultiMap<Class<? extends InspiredGov>, InspiredGov>(); 
	public HashMap<String, PlayerData> playerdata = new HashMap<String, PlayerData>();
	public GlobalGov global = new GlobalGov();
	public TempCommandListener CM = new TempCommandListener(this);
	public TempPlayerListener PL = new TempPlayerListener(this);
	
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		this.regiondata.get(Country.class);
		SS.Start();
		pm.registerEvents(PL, this);
		this.getCommand("hud").setExecutor(CM);
		this.getCommand("map").setExecutor(CM);
	}
	
	public void onDisable() {
		SS.Stop();
	}
	
	public class TempPlayerListener implements Listener {
		
		InspiredNations plugin;
		
		public TempPlayerListener(InspiredNations instance) {
			plugin = instance;
		}
		
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
			if(!plugin.playerdata.containsKey(event.getPlayer().getName())) {
				plugin.playerdata.put(event.getPlayer().getName(), new PlayerData(event.getPlayer().getName()));
				System.out.println("Player has not been added to playerdata yet");
			}
		}
	}
	
	public class TempCommandListener implements CommandExecutor {

		InspiredNations plugin;
		
		public TempCommandListener(InspiredNations instance) {
			plugin = instance;
		}
		
		@Override
		public boolean onCommand(CommandSender sender, Command arg1, String CommandLable,
				String[] arg3) {
			if(!(sender instanceof Player)) {
				plugin.logger.info("HUD cannot be called from console.");
				return false;
			}
			
			PlayerData PDI = plugin.playerdata.get(sender.getName());
			if (CommandLable.equalsIgnoreCase("hud")) {
				// Handles Commands
				ConversationBuilder convo = new ConversationBuilder(plugin);
				Conversation conversation = convo.HudConvo(PDI);
				PDI.setCon(conversation);
				conversation.begin();
			}
			else if(CommandLable.equalsIgnoreCase("map")) {
				ConversationBuilder convo = new ConversationBuilder(plugin);
				Conversation conversation = convo.MapConvo(PDI);
				PDI.setCon(conversation);
				conversation.begin();
			}
			else return false;
			return false;
		}
		
	}
}
