package com.github.InspiredOne.InspiredNations.Economy.Implem;

import org.bukkit.inventory.ItemStack;

public interface ItemBuyer extends Buyer {
	public void recieveItem(ItemStack item);
}
