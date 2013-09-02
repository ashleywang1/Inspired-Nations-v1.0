package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class TabListener extends InspiredListener {

	public TabListener(ActionManager manager) {
		super(manager);
	}

	@EventHandler
	public void onChatTabPress(PlayerChatTabCompleteEvent event) {
		if(event.getLastToken().equals("=") || event.getLastToken().equals("+") || event.getLastToken().equals("-")) {
			event.getTabCompletions().clear();
			this.getManager().Update();
		}
	}
	
}
