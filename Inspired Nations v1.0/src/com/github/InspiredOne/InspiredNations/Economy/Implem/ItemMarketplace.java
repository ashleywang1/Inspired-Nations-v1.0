package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.ToolBox.Sellable;

public class ItemMarketplace implements MarketPlace {

	public static final String name = "Item Marketplace";
	
	public ItemMarketplace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Sellable> getSales() {
		
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return name;
	}

}
