package com.github.InspiredOne.InspiredNations.Regions;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;

public abstract class Region {

	
	public abstract boolean isIn(Region region);
	/**Returns true if the entire region is within the input region*/
	
	public abstract double volume();
	/**Returns the volume in cubic meters*/
	
	public abstract double area();
	/**Returns the area in square meters*/
	
	public abstract boolean contains(Location location);
	/**Returns true if the location is within the region*/
	
	public abstract boolean intersects(Region region);
	/**Returns true if the regions overlap*/
	
	public abstract String getTypeName();
	/**Returns the type name to be used in menus*/
	
	public abstract String getDescription();
	/**Returns the description to be used in menus*/
	
	public abstract Class<? extends ActionMenu> getClaimConvo();
	/**Returns the first menu of the chain of menus to claim it.
	 * Origin is the prompt that launches into these prompts.
	 * End is the prompt to go to after finishing the claim.*/
}
