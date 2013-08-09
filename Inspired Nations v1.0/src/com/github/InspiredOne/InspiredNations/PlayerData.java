package com.github.InspiredOne.InspiredNations;

import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;


public class PlayerData {

	private InspiredNations plugin;
	private Conversation con;
	private String name;
	
	public PlayerData(InspiredNations instance, String name) {
		this.setName(name);
		plugin = instance;
	}

	public Conversation getCon() {
		
		return con;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player getPlayer() {
		return plugin.getServer().getPlayer(name);
	}
	
}
