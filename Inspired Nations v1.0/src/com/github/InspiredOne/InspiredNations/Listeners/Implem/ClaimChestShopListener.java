package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.InspiredOne.InspiredNations.Exceptions.BlockNotChestException;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class ClaimChestShopListener extends InspiredListener<ClaimChestShopManager> {

	

	
	public ClaimChestShopListener(ClaimChestShopManager manager) {
		super(manager);
	}

	@EventHandler
	public void onPlayerLeftClick(PlayerInteractEvent event) {
		if(this.getPlayerData().getPlayer() != event.getPlayer()) {
			return;
		}
		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			try {
				this.manager.addBlock(event.getClickedBlock());
			} catch (BlockNotChestException e) {

			}
			if(event.isBlockInHand()) {
				event.setCancelled(true);
			}
			manager.Update();
		}
	}
	
	
}
