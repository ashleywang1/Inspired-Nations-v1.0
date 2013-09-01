package com.github.InspiredOne.InspiredNations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiMap;


public class StartStop {

	// Grabbing the instance of the plugin
	InspiredNations plugin;
	
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
	        plugin.regiondata = (MultiMap<Class<? extends InspiredGov>, InspiredGov>) rin.readObject();
	        rin.close();
	        regionIn.close();
	        
	        File playerfile = new File(plugin.getDataFolder(), "playerdata.yml");
	        FileInputStream playerIn = new FileInputStream(playerfile);
	        ObjectInputStream pin = new ObjectInputStream(playerIn);
	        plugin.playerdata = (HashMap<String, PlayerData>) pin.readObject();
	        pin.close();
	        playerIn.close();
	        
	        File econfile = new File(plugin.getDataFolder(), "econdata.yml");
	        FileInputStream econIn = new FileInputStream(econfile);
	        ObjectInputStream ein = new ObjectInputStream(econIn);
	        plugin.Exchange = (HashMap<Currency, BigDecimal>) ein.readObject();
	        ein.close();
	        econIn.close();
		}
		catch(Exception ex) {
			
		}
		
		// Handles online players
		for(Player player:plugin.getServer().getOnlinePlayers()) {
			if(!plugin.playerdata.containsKey(player.getName())) {
				plugin.playerdata.put(player.getName(), new PlayerData(player.getName()));
			}
		}
		
	}
	
	// Handles Shut-down of plugin
	public void Stop() {
		try {
			Player[] online = plugin.getServer().getOnlinePlayers();
			for (int i = 0; i < online.length; i++) {
				if (online[i].isConversing()) {
					String name = online[i].getName();
					online[i].abandonConversation(plugin.playerdata.get(name).getCon());
					plugin.playerdata.get(name).setCon(null);
				}
			}
		}
		catch (Exception ex) {	
		}
		
		// Saves Data
		try {
			File regionfile = new File(plugin.getDataFolder(), "regiondata.yml");
	        FileOutputStream regionOut = new FileOutputStream(regionfile);
	        ObjectOutputStream rout = new ObjectOutputStream(regionOut);
	        rout.writeObject(plugin.regiondata);
	        rout.close();
	        regionOut.close();
	        
	        File playerfile = new File(plugin.getDataFolder(), "playerdata.yml");
	        FileOutputStream playerOut = new FileOutputStream(playerfile);
	        ObjectOutputStream pout = new ObjectOutputStream(playerOut);
	        pout.writeObject(plugin.playerdata);
			pout.close();
			playerOut.close();
			
	        File econfile = new File(plugin.getDataFolder(), "econdata.yml");
	        FileOutputStream econOut = new FileOutputStream(econfile);
	        ObjectOutputStream eout = new ObjectOutputStream(econOut);
	        eout.writeObject(plugin.Exchange);
	        eout.close();
	        econOut.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been disabled.");
	}
}
