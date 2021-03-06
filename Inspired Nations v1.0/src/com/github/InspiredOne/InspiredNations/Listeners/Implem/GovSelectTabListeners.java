package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class GovSelectTabListeners<T extends GovSelectTabManager<?>> extends InspiredListener<T> {

	public GovSelectTabListeners(T manager) {
		super(manager);
	}
	
	@EventHandler
	public void onChatTabPress(PlayerChatTabCompleteEvent event) {
		this.getManager(); event.getLastToken();
	}

}
