package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class GovSelectTabListeners extends InspiredListener {

	public GovSelectTabListeners(ActionManager manager) {
		super(manager);
	}
	
	@EventHandler
	public void onChatTabPress(PlayerChatTabCompleteEvent event) {
		this.getManager(); event.getLastToken();
	}

}
