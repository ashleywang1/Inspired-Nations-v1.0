package com.github.InspiredOne.InspiredNations.Economy.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public interface Buyer extends Nameable, Payable {
	public Location getLocation();
	public Currency getCurrency();
}
