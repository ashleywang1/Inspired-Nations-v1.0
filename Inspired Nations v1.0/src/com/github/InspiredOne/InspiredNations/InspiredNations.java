
package com.github.InspiredOne.InspiredNations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.MoneyExchange;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiGovMap;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class InspiredNations extends JavaPlugin {

	public static InspiredNations plugin = (InspiredNations) Bukkit.getPluginManager().getPlugin("InspiredNations");
	public Logger logger = Logger.getLogger("Minecraft"); // Variable to communicate with console
	private StartStop SS = new StartStop(this); // Deals with start-up and shut-down
	public static MultiGovMap regiondata = new MultiGovMap(); 
	public static HashMap<PlayerID, PlayerData> playerdata = new HashMap<PlayerID, PlayerData>();
	public static MoneyExchange Exchange = new MoneyExchange();
	public static List<MarketPlace> Markets = new ArrayList<MarketPlace>(); 
	public static GlobalGov global = (GlobalGov) (new GovFactory(GlobalGov.class)).withMoneyname("Coin").withDiamondValue(new BigDecimal(1000)).getGov();
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
		
		for(HashSet<InspiredGov> set:regiondata.values()) {
			for(Iterator<InspiredGov> iter = set.iterator(); iter.hasNext();){
				InspiredGov gov = iter.next();
				System.out.println(gov.getName());
			}
		}
		for(PlayerID player:playerdata.keySet()) {
			System.out.println(player);
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
			System.out.println(new PlayerID(event.getPlayer()).equals(new PlayerID(event.getPlayer())));
			if(!InspiredNations.playerdata.containsKey(new PlayerID(event.getPlayer()))) {
				InspiredNations.playerdata.put(new PlayerID(event.getPlayer()), new PlayerData(event.getPlayer().getName()));
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
			
			PlayerData PDI = InspiredNations.playerdata.get(new PlayerID((Player) sender));
			if (CommandLable.equalsIgnoreCase("hud")) {
				// Handles Commands
				if(PDI.getPlayer().isConversing()) {
					return false;
				}
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
