package com.github.InspiredOne.InspiredNations.Economy;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.ToolBox.Sellable;

public abstract class MarketPlace {
	
	private List<Sellable> ForSale = new ArrayList<Sellable>();
	private String marketName; // Used in menus
	
	public MarketPlace(String marketName) {
		this.marketName = marketName;
	}
	
	public String getName() {
		return this.marketName;
	}
	/**
	 * 
	 * @param searchPhrase
	 * @return	a list of all the sellables that have the searchPhrase in the description
	 */
	public List<Sellable> searchDesc(String searchPhrase) {
		List<Sellable> output = new ArrayList<Sellable>();
		
		for(Sellable check:ForSale) {
//			if(check.getDescription().contains(searchPhrase)) {
//				output.add(check);
//			}
		}
		return output;
	}
	
}
