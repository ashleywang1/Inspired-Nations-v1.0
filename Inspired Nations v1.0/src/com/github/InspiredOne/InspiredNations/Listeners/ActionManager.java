package com.github.InspiredOne.InspiredNations.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.HandlerList;

import com.github.InspiredOne.InspiredNations.Debug;
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
public abstract class ActionManager<T extends ActionMenu> {

	private T menu;
	protected List<InspiredListener<?>> listeners = new ArrayList<InspiredListener<?>>();

	public ActionManager(T menu) {
		this.menu = menu;
	}
	/**
	 * Starts the PlayerListener associated with this operation.
	 */
	public void startListening() {
		for(InspiredListener<?> listener:this.getPlayerListener()) {
			InspiredNations.plugin.getServer().getPluginManager().registerEvents(listener, InspiredNations.plugin);
		}
	}
	/**
	 * Shuts down the PlayerListener associated with this operation.
	 */
	public void stopListening() {
		Debug.print("Inside Stop Listening");
		for(InspiredListener<?> listener:this.getPlayerListener()) {
			HandlerList.unregisterAll(listener);
		}
	}
	
	/**
	 * 
	 * @return	a <code>List</code> of <code>InspiredPlayerListener</code>s used by this manager
	 */
	public List<InspiredListener<?>> getPlayerListener() {
		return listeners;
	}
	
	public T getActionMenu() {
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
