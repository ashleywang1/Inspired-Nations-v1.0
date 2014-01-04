package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.Listeners.TabManager;

public class TabListener extends InspiredListener {

	public TabListener(TabManager manager) {
		super(manager);
		
	}
	
	@Override
	public TabManager getManager() {
		return (TabManager) this.manager;
	}

	@EventHandler
	public void onChatTabPress(PlayerChatTabCompleteEvent event) {
		if(this.getPlayerData().getPlayer() != event.getPlayer()) {
			return;
		}
		event.getTabCompletions().clear();
		this.getManager().preTabEntry = event.getLastToken();
		this.getManager().Update();
	}
	
}
