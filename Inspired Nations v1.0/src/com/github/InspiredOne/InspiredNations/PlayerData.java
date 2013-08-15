package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;

import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;

import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;


public class PlayerData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8628182579123244877L;
	
	private transient Conversation con;
	private String name;
	
	public PlayerData(String name) {
		this.setName(name);
		con = null;
	}

	public Conversation getCon() {
		return con;
	}
	
	public void setCon(Conversation con) {
		this.con = con;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player getPlayer(InspiredNations plugin) {
		return plugin.getServer().getPlayer(name);
	}
	
	public boolean isSubjectOf(InspiredNations plugin, Class<? extends InspiredGov> govtype) {
		for(InspiredGov gov:plugin.regiondata.get(govtype)) {
			if(gov.getSubjects().contains(this.getName())) {
				return true;
			}
		}
		return false;
		
	}
	
}
