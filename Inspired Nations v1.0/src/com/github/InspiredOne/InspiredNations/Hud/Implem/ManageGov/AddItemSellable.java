package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemSellable;
import com.github.InspiredOne.InspiredNations.Exceptions.NoShopRegionException;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class AddItemSellable extends TabSelectOptionMenu<ItemSellable> {

	ChestShop shop;
	Menu previous;
	
	public AddItemSellable(PlayerData PDI, Menu previous, ChestShop shop) {
		super(PDI);
		this.shop = shop;
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		try {
			for(ItemStack stack:shop.getInventory()) {
				if(stack != null ) {
					stack = stack.clone();
					stack.setAmount(1);
					if(!this.taboptions.contains(new ItemSellable(stack, shop))) {
						this.taboptions.add(new ItemSellable(stack, shop));
					}
				}
			}
		} catch (NoShopRegionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new AddItemSellable(PDI, previous, shop);
	}

	@Override
	public String getHeader() {
		return "Pick Item To Sell";
	}

}
