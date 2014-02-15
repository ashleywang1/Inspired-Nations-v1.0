package com.github.InspiredOne.InspiredNations.Economy;

import java.util.List;

import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Sellable;

public interface MarketPlace extends Nameable{
	
	/**
	 * Gets all the sellables in the server. This method is responsible for checking
	 * every location that a sellable can exist and adding it to the marketplace
	 * if the sellable is for sale.
	 * @return
	 */
	public abstract List<Sellable> getSales();

	/**
	 * Gets the name of the marketplace to be used in Menus
	 * @return
	 */
	public abstract String getName();
}
