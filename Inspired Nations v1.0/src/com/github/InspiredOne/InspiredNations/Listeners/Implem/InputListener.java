package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class InputListener extends InspiredListener {

	public InputListener(InputManager manager) {
		super(manager);
	}
	
	@EventHandler
	public void onPlayerTabComplete(PlayerChatTabCompleteEvent event) {
		if(((InputManager) this.getManager()).getTabOptions().size() > 0) {
			event.getTabCompletions().clear();
			event.getTabCompletions().addAll(((InputManager) this.getManager()).getTabOptions());
			this.getManager().Update();
		}
	}
	
}
