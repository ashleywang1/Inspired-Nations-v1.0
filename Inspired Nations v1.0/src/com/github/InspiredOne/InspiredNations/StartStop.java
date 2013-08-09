package com.github.InspiredOne.InspiredNations;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;


public class StartStop {

	// Grabbing the instance of the plugin
	InspiredNations plugin;
	
	public StartStop(InspiredNations instance) {
		plugin = instance;
	}
	
	// Handles Start-up of plugin
	public void Start() {
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been enabled.");
		
		// Handles config.yml
		FileConfiguration config = plugin.getConfig(); 
		config.options().copyDefaults(true);
		if (!(new File(plugin.getDataFolder() + config.getName()).exists())) {
			plugin.saveDefaultConfig();
		}
		
	}
	
	// Handles Shut-down of plugin
	public void Stop() {
		try {
			Player[] online = plugin.getServer().getOnlinePlayers();
			for (int i = 0; i < online.length; i++) {
				if (online[i].isConversing()) {
					String name = online[i].getName();
				//	online[i].abandonConversation(plugin.playerdata.get(name).getConversation());
				}
			}
		}
		catch (Exception ex) {	
		}
		PluginDescriptionFile pdf = plugin.getDescription();
		plugin.getLogger().info(pdf.getName() + " version " + pdf.getVersion() + " has been disabled.");
	}
}
