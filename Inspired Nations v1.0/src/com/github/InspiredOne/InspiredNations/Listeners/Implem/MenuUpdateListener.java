package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Hud.MenuUpdateEvent;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class MenuUpdateListener<T extends ActionManager<?>> extends InspiredListener<T> {

	public MenuUpdateListener(T manager) {
		super(manager);
	}

	@EventHandler
	public void onMenuUpdate(MenuUpdateEvent event) {
		Debug.print("Inside Menu Update event");
		Debug.print("Target null? " + (event.getTarget() == null));
		Debug.print("PlayerDataNull? " + (manager.getPlayerData()==null));
		if(event.getTarget().equals(manager.getPlayerData().getPlayerID())) {
			Debug.print("Update has been called!");
			this.getManager().Update();;
		}
	}
}
