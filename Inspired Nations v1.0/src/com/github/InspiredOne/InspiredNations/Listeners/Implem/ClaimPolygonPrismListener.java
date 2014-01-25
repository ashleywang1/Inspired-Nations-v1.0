package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class ClaimPolygonPrismListener<T extends ClaimPolygonPrismManager<?>> extends InspiredListener<T> {

	public ClaimPolygonPrismListener(T manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}
	
	@EventHandler
	public void onPlayerClickBlock(PlayerInteractEvent event) {
		if(this.getPlayerData().getPlayer() != event.getPlayer()) {
			return;
		}
		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(!event.isBlockInHand()) {
				event.setCancelled(true);
			}
		}
	}

}
