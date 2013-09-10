
package com.github.InspiredOne.InspiredNations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
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

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiGovMap;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiMap;

public class InspiredNations extends JavaPlugin {

	public static InspiredNations plugin = (InspiredNations) Bukkit.getPluginManager().getPlugin("InspiredNations");
	public Logger logger = Logger.getLogger("Minecraft"); // Variable to communicate with console
	private StartStop SS = new StartStop(this); // Deals with start-up and shut-down
	public MultiGovMap regiondata = new MultiGovMap(); 
	public HashMap<String, PlayerData> playerdata = new HashMap<String, PlayerData>();
	public HashMap<Currency, BigDecimal> Exchange = new HashMap<Currency, BigDecimal>();
	public GlobalGov global = (GlobalGov) (new GovFactory(GlobalGov.class)).withMoneyname("Coin").withMoneyMultiplyer(BigDecimal.ONE).getGov();
	public TempCommandListener CM = new TempCommandListener(this);
	public TempPlayerListener PL = new TempPlayerListener(this);
	
	public void onEnable() {
		InspiredNations.plugin = this;
		PluginManager pm = this.getServer().getPluginManager();
		SS.Start();
		pm.registerEvents(PL, this);
		global.register();

		if(regiondata.get(global.getClass()).isEmpty()) {
			regiondata.put(global.getClass(), global);
		}
		else {
			global = (GlobalGov) regiondata.get(global.getClass()).iterator().next();
		}

		this.getCommand("hud").setExecutor(CM);
		this.getCommand("map").setExecutor(CM);
		
		for(HashSet<InspiredGov> set:this.regiondata.values()) {
			for(Iterator<InspiredGov> iter = set.iterator(); iter.hasNext();){
				InspiredGov gov = iter.next();
				System.out.println(gov.getName());
			}
		}
		for(String player:this.playerdata.keySet()) {
			System.out.println(player);
		}
		for(Currency currency:this.Exchange.keySet()) {
			System.out.println(currency.getName());
		}
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
				ConversationBuilder convo = new ConversationBuilder(PDI);
				Conversation conversation = convo.HudConvo();
				PDI.setCon(conversation);
				conversation.begin();
			}
			else if(CommandLable.equalsIgnoreCase("map")) {
				ConversationBuilder convo = new ConversationBuilder(PDI);
				Conversation conversation = convo.MapConvo();
				PDI.setCon(conversation);
				conversation.begin();
			}
			else return false;
			return false;
		}
		
	}
}
