package com.github.InspiredOne.InspiredNations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.InspiredOne.InspiredNations.Economy.MoneyExchange;
import com.github.InspiredOne.InspiredNations.Economy.TaxTimer;
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
	        
/*	        File playerfile = new File(plugin.getDataFolder(), "playerdata.yml");
	        FileInputStream playerIn = new FileInputStream(playerfile);
	        ObjectInputStream pin = new ObjectInputStream(playerIn);
	        InspiredNations.playerdata = (IndexedMap<PlayerID, PlayerData>) pin.readObject();
	        pin.close();
	        playerIn.close();
	        
	        File econfile = new File(plugin.getDataFolder(), "econdata.yml");
	        FileInputStream econIn = new FileInputStream(econfile);
	        ObjectInputStream ein = new ObjectInputStream(econIn);
	        InspiredNations.Exchange = (MoneyExchange) ein.readObject();
	        ein.close();
	        econIn.close();
	        
	        File taxfile = new File(plugin.getDataFolder(), "tax.yml");
	        FileInputStream taxIn = new FileInputStream(taxfile);
	        ObjectInputStream tin = new ObjectInputStream(taxIn);
	        InspiredNations.taxTimer = (TaxTimer) tin.readObject();
	        tin.close();
	        taxIn.close();*/
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
/*		        
		        File playerfile = new File(plugin.getDataFolder(), "playerdata.yml");
		        FileOutputStream playerOut = new FileOutputStream(playerfile);
		        ObjectOutputStream pout = new ObjectOutputStream(playerOut);
		        pout.writeObject(InspiredNations.playerdata);
				pout.close();
				playerOut.close();
				
		        File econfile = new File(plugin.getDataFolder(), "econdata.yml");
		        FileOutputStream econOut = new FileOutputStream(econfile);
		        ObjectOutputStream eout = new ObjectOutputStream(econOut);
		        eout.writeObject(InspiredNations.Exchange);
		        eout.close();
		        econOut.close();
		        
		        File taxfile = new File(plugin.getDataFolder(), "tax.yml");
		        FileOutputStream taxOut = new FileOutputStream(taxfile);
		        ObjectOutputStream tout = new ObjectOutputStream(taxOut);
		        tout.writeObject(InspiredNations.taxTimer);
		        tout.close();
		        taxOut.close();*/
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}		
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been disabled.");
	}
}
