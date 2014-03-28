package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemSellable;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ManageItemsForSale extends TabSelectOptionMenu<ItemSellable> {

	ChestShop shop;
	Menu previous;
	public ManageItemsForSale(PlayerData PDI, Menu previous, ChestShop shop) {
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
	public String getHeader() {
		return "";
	}

	@Override
	public void addTabOptions() {
		Debug.print(1);
		for(ItemSellable item:shop.getItems()) {
			Debug.print(2);
			this.taboptions.add(item);
			Debug.print(3);
		}
		
	}

	@Override
	public void addOptions() {
		Debug.print(4);
		this.options.add(new PromptOption(this, "Add Items To Sell", new AddItemSellable(PDI, this, shop)));
		Debug.print(5);
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
