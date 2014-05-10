package com.github.InspiredOne.InspiredNations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.math.BigDecimal;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.MoneyExchange;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Economy.NodeRef;
import com.github.InspiredOne.InspiredNations.Economy.TaxTimer;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiGovMap;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;


public class StartStop {

	// Grabbing the instance of the plugin
	InspiredNations plugin;
	static Instrumentation inst ;
	
	public StartStop(InspiredNations instance) {
		plugin = instance;
	}
	
	public void EconMap() {
		YamlConfiguration file = null;
		file = new YamlConfiguration();
		File utilityfile = new File(plugin.getDataFolder(), "utilityfunction.yml");
		file.options().copyDefaults(true);
		NodeRef ref = new NodeRef();
		ref.Begin.writeToConfig("top.", file);
		try {
			file.save(utilityfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Handles Start-up of plugin
	@SuppressWarnings("unchecked")
	public void Start() {
		
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been enabled.");
		
		// Handles config.yml
		FileConfiguration config = plugin.getConfig(); 
		config.options().copyDefaults(true);
		if (!(new File(plugin.getDataFolder() + config.getName()).exists())) {
			plugin.saveDefaultConfig();
		}
		
		// Loads Data
		try {
			File regionfile = new File(plugin.getDataFolder(), "regiondata.yml");
	        FileInputStream regionIn = new FileInputStream(regionfile);
	        ObjectInputStream rin = new ObjectInputStream(regionIn);
	        InspiredNations.regiondata = (MultiGovMap) rin.readObject();
	        InspiredNations.playerdata = (IndexedMap<PlayerID, PlayerData>) rin.readObject();
	        InspiredNations.Exchange = (MoneyExchange) rin.readObject();
	        InspiredNations.taxTimer = (TaxTimer) rin.readObject();
	        rin.close();
	        regionIn.close();
		}
		catch(Exception ex) {
			
		}
		
		// Handles online players
		for(Player player:plugin.getServer().getOnlinePlayers()) {
			PlayerID ID = new PlayerID(player);
			if(!InspiredNations.playerdata.containsKey(ID)) {
				InspiredNations.playerdata.put(ID, new PlayerData(ID));
			}
		}
		
		// Starts up the TaxTimer
		InspiredNations.taxTimer.startTimer();
		
	}
	
	// Handles Shut-down of plugin
	public void Stop() {
		//TODO Delete this. It resets the NPC money every time the server is reloaded
		
		for(PlayerData PDI:InspiredNations.playerdata.values()) {
			for(NPC npc:PDI.npcs) {
				try {
					npc.addMoney(new BigDecimal(100), Currency.DEFAULT);
				} catch (NegativeMoneyTransferException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// End of things to delete
		this.EconMap();
		try {
			Player[] online = plugin.getServer().getOnlinePlayers();
			for (int i = 0; i < online.length; i++) {
				if (online[i].isConversing()) {
					PlayerID onlineP = new PlayerID(online[i]);
					PlayerData.unRegister(onlineP);
				}
			}
		}
		catch (Exception ex) {	
		}
		// Checks to see if save folder has been deleted
		FileConfiguration config = plugin.getConfig(); 
		if ((new File(plugin.getDataFolder() + config.getName()).exists())) {
			
			// Saves Data
			try {
				File regionfile = new File(plugin.getDataFolder(), "regiondata.yml");
		        FileOutputStream regionOut = new FileOutputStream(regionfile);
		        ObjectOutputStream rout = new ObjectOutputStream(regionOut);
		        rout.writeObject(InspiredNations.regiondata);
		        rout.writeObject(InspiredNations.playerdata);
		        rout.writeObject(InspiredNations.Exchange);
		        rout.writeObject(InspiredNations.taxTimer);
		        rout.close();
		        regionOut.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}		
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been disabled.");
	}
}
