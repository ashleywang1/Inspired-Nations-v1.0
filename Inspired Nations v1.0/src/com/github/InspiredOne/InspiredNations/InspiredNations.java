
package com.github.InspiredOne.InspiredNations;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.MoneyExchange;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Economy.TaxTimer;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemMarketplace;
import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiGovMap;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class InspiredNations extends JavaPlugin {

	public static InspiredNations plugin = (InspiredNations) Bukkit.getPluginManager().getPlugin("InspiredNations");
	public Logger logger = Logger.getLogger("Minecraft"); // Variable to communicate with console
	private StartStop SS = new StartStop(this); // Deals with start-up and shut-down
	public static MultiGovMap regiondata = new MultiGovMap(); 
	public static IndexedMap<PlayerID, PlayerData> playerdata = new IndexedMap<PlayerID, PlayerData>();
	public static MoneyExchange Exchange = new MoneyExchange();
	public static List<MarketPlace<?>> Markets = new ArrayList<MarketPlace<?>>(); 
	public static GlobalGov global;
	public static TaxTimer taxTimer;
	public TempCommandListener CM = new TempCommandListener(this);
	public TempPlayerListener PL = new TempPlayerListener(this);
	public static ArrayList<String> check = new ArrayList<String>();
	
	public void onEnable() {

		InspiredNations.plugin = this;
		taxTimer = new TaxTimer();
		PluginManager pm = this.getServer().getPluginManager();
		SS.Start();
		if(InspiredNations.Markets.isEmpty()) {
			InspiredNations.Markets.add(new ItemMarketplace());
		}
		taxTimer.setCycleLength(InspiredNations.plugin.getConfig().getInt("tax_cycle_length"));
		InspiredNations.Exchange.registerCurrency(Currency.DEFAULT, new BigDecimal(500));
		pm.registerEvents(PL, this);
		global = GovFactory.getGovInstance(GlobalGov.class);

		global.register();
		// if this is first time running plugin, then add the default globalgov to the regiondata
		// else, put the global gov loaded in the region data back into the global variable.
		if(regiondata.get(global.getClass()).isEmpty()) {
			regiondata.putValue(global.getClass(), global);
		}
		else {
			global = (GlobalGov) regiondata.get(global.getClass()).iterator().next();
		}
		global.setName("Global");
		this.getCommand("hud").setExecutor(CM);
		this.getCommand("map").setExecutor(CM);
		this.getCommand("npc").setExecutor(CM);
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
		Debug.print(HandlerList.getRegisteredListeners(InspiredNations.plugin).size() + "= handlerlist size");
		SS.Stop();
	}
	
	public class TempPlayerListener implements Listener {
		
		InspiredNations plugin;
		
		public TempPlayerListener(InspiredNations instance) {
			plugin = instance;
		}
		
		@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
			if(!InspiredNations.playerdata.containsKey(new PlayerID(event.getPlayer()))) {
				PlayerID ID = new PlayerID(event.getPlayer());
				InspiredNations.playerdata.put(ID, new PlayerData(ID));
				System.out.println("Player has not been added to playerdata yet");
			}
		}
		@EventHandler
		public void onPlayerLeave(PlayerKickEvent event) {
			PlayerID id = new PlayerID(event.getPlayer());
			PlayerData.unRegister(id);
		}
		@EventHandler
		public void onPlayerLeave(PlayerQuitEvent event) {
			PlayerID id = new PlayerID(event.getPlayer());
			PlayerData.unRegister(id);
		}
		@EventHandler
		public void onPlayerChat(AsyncPlayerChatEvent event) {
			String msg = event.getMessage();
			event.setCancelled(true);
			InspiredNations.playerdata.get(new PlayerID(event.getPlayer())).getMsg().sendChatMessage(msg);;
		}
		@EventHandler
		public void onPlayerMove(PlayerMoveEvent event) {
			PlayerData PDI = InspiredNations.playerdata.get(new PlayerID(event.getPlayer()));
			PDI.setLocation(event.getTo());
		}
		@EventHandler
		public void onPlayerBreakBlock(BlockBreakEvent event) {
			Debug.print("Player is breaking block.");
			PlayerData player = InspiredNations.playerdata.get(new PlayerID(event.getPlayer()));
			if(!player.getAllowedInteract(event.getBlock().getLocation())) {
				Debug.print(player.getName() + " is trying to break a block s/he is not allowed to.");
				event.setCancelled(true);
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
				try {
					if(PDI.getPlayer().isConversing()) {
						return false;
					}
				} catch (PlayerOfflineException e) {
					e.printStackTrace();
				}
				ConversationBuilder convo = new ConversationBuilder(PDI);
				Conversation conversation = convo.HudConvo();
				PDI.setCon(conversation);
				conversation.begin();
			}
			else if(CommandLable.equalsIgnoreCase("map")) {
				try {
					if(PDI.getPlayer().isConversing()) {
						return false;
					}
				} catch (PlayerOfflineException e) {
					e.printStackTrace();
				}
				ConversationBuilder convo = new ConversationBuilder(PDI);
				Conversation conversation = convo.MapConvo();
				PDI.setCon(conversation);
				conversation.begin();
			}
			else if(CommandLable.equalsIgnoreCase("npc")) {
				for(NPC npc:PDI.npcs) {
					npc.buyOut();
				}
			}
			else return false;
			return false;
		}
		
	}
	
	public static PlayerData getTestingPlayer() {
		return InspiredNations.playerdata.get(InspiredNations.playerdata.getIndex(0));
	}
}
