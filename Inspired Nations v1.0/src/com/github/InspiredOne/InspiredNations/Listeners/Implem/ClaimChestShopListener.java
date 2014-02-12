package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.InspiredOne.InspiredNations.Economy.ItemSellable;
import com.github.InspiredOne.InspiredNations.Exceptions.BlockNotChestException;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

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
				this.getManager().getActionMenu().setError(MenuError.SELECTION_MUST_BE_CHEST());
			}
			if(event.isBlockInHand()) {
				event.setCancelled(true);
			}
			event.getPlayer().getInventory().setItem(1, event.getPlayer().getItemInHand());
			ItemSellable item = new ItemSellable(event.getPlayer().getItemInHand());
			event.getPlayer().getInventory().setItem(0, item.getItem());
			manager.Update();
		}
		
		
	}
	
	
}
