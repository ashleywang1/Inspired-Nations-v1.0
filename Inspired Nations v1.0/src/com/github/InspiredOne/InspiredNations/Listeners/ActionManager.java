package com.github.InspiredOne.InspiredNations.Listeners;

import java.util.List;

import org.bukkit.event.HandlerList;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
/**
 * Deals with menu inputs that are actions rather than chat entries. Each
 * <code>ActionManager</code> has a collection of <code>InspiredPlayerListener</code>s
 * that can pass information into the manager, update the menu, and interact with the
 * player. For each <code>ActionMenu</code> there is one <code>ActionManager</code>.
 * @author Jedidiah E. Phillips
 *
 */
public abstract class ActionManager {

	private ActionMenu menu;
	public InspiredNations plugin;
	public ActionManager(InspiredNations instance, ActionMenu menu)	 {
		this.menu = menu;
		this.plugin = instance;
	}
	/**
	 * 
	 * @return	a <code>List</code> of <code>InspiredPlayerListener</code>s used by this manager
	 */
	public abstract List<InspiredListener> getPlayerListener();
	/**
	 * Starts the PlayerListener associated with this operation.
	 */
	public void startListening() {
		for(InspiredListener listener:this.getPlayerListener()) {
			plugin.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	/**
	 * Shuts down the PlayerListener associated with this operation.
	 */
	public void stopListening() {
		for(InspiredListener listener:this.getPlayerListener()) {
			HandlerList.unregisterAll(listener);
		}
	}
	
	public ActionMenu getActionMenu() {
		return menu;
	}
	
	public PlayerData getPlayerData() {
		return this.menu.getPlayerData();
	}
	/**
	 * Updates the menu view for the player. 
	 */
	public void Update() {
		this.getActionMenu().Update();
	}
}
