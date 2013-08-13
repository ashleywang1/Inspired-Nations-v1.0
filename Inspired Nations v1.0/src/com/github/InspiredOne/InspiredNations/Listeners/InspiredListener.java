package com.github.InspiredOne.InspiredNations.Listeners;

import org.bukkit.event.Listener;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;

public class InspiredListener implements Listener {

	protected InspiredNations plugin;
	private ActionManager manager;
	
	public InspiredListener(InspiredNations instance, ActionManager manager) {
		setPlugin(instance);
		this.setManager(manager);
	}
	
	public PlayerData getPlayerData() {
		return this.manager.getPlayerData();
	}

	public ActionManager getManager() {
		return manager;
	}

	public void setManager(ActionManager manager) {
		this.manager = manager;
	}

	public InspiredNations getPlugin() {
		return plugin;
	}

	public void setPlugin(InspiredNations plugin) {
		this.plugin = plugin;
	}
	
}
