package com.github.InspiredOne.InspiredNations.Regions;

import java.io.Serializable;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;

public abstract class Region implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -330203131653502896L;
	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return	
	 */
	public abstract boolean isIn(Region region);
	
	/**
	 * Returns the volume in cubic meters
	 * @return
	 */
	public abstract double volume();
	/**
	 * Returns the area in square meters
	 * @return
	 */
	public abstract double area();
	/**
	 * Returns true if the location is within the region
	 * @param location	the location to test
	 * @return
	 */
	public abstract boolean contains(Location location);
	/**
	 * Returns true if the regions overlap
	 * @param region
	 * @return
	 */
	public abstract boolean intersects(Region region);
	/**
	 * Returns the type name to be used in menus
	 * @return
	 */
	public abstract String getTypeName();
	/**
	 * Returns the description to be used in menus
	 * @return 
	 */
	public abstract String getDescription();
	/**
	 * Returns the first of the chain of menus used to claim it.
	 * @param PDI	The player that is claiming land.
	 * @param previous	the menu to go to after everything is done.
	 * @return
	 */
	public abstract Menu getClaimMenu(PlayerData PDI, Menu previous);
	/**Returns the first menu of the chain of menus to claim it.
	 * Origin is the prompt that launches into these prompts.
	 * End is the prompt to go to after finishing the claim.*/
}
