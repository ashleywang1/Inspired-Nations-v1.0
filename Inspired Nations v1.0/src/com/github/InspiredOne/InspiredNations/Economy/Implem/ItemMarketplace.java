package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Governments.Implem.GoodBusiness;

public class ItemMarketplace implements MarketPlace<ItemSellable> {

	public static final String name = "Item Marketplace";
	
	public ItemMarketplace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItemSellable> getSales() {
		List<ItemSellable> output = new ArrayList<ItemSellable>();
		
		for(InspiredGov busi:InspiredNations.regiondata.get(GoodBusiness.class)) {
			GoodBusiness good = (GoodBusiness) busi;
			for(Facility fac:good.getFacilities()) {
				if(fac instanceof ChestShop) {
					for(ItemSellable item:((ChestShop) fac).getItems()) {
						if(item.isForSale()) {
							output.add(item);
						}
					}
				}
			}
		}
		
		return output;
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
