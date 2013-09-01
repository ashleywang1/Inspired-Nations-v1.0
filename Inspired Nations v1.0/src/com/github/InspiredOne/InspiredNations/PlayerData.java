package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;

import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;


public class PlayerData implements Serializable, Nameable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8628182579123244877L;
	
	private transient Conversation con;
	private String name;
	private IndexedMap<Account, String> accounts = new IndexedMap<Account, String>();
	
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
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public Player getPlayer() {
		InspiredNations plugin = InspiredNations.plugin;
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
	
	public List<InspiredGov> getCitizenship(Class<? extends InspiredGov> govType) {
		List<InspiredGov> output = new ArrayList<InspiredGov>();
		InspiredNations plugin = InspiredNations.plugin;
		
		for(InspiredGov gov:plugin.regiondata.get(govType)) {
			if(gov.getSubjects().contains(this.getName())) {
				output.add(gov);
			}
		}
		
		return output;
	}

	public IndexedMap<Account, String> getAccounts() {
		return accounts;
	}

	public void setAccounts(IndexedMap<Account, String> accounts) {
		this.accounts = accounts;
	}
}
