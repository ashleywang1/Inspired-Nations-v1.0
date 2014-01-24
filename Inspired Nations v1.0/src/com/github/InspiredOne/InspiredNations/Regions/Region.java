package com.github.InspiredOne.InspiredNations.Regions;

import java.io.Serializable;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public abstract class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -330203131653502896L;
	
	public Region() {
		
	}
	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return	
	 */
	public abstract boolean IsIn(Region region);
	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return
	 */
	public abstract boolean IsIn(NonCummulativeRegion region);
	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return	
	 * @throws IncorrectUnitOfTheCummulativeRegion 
	 */
	public abstract boolean IsIn(CummulativeRegion region);

	/**
	 * Returns the volume in cubic meters
	 * @return
	 */
	public abstract int volume();
	/**
	 * Returns true if the location is within the region
	 * @param location	the location to test
	 * @return
	 */
	public boolean contains(Location location) {
		return this.contains(new Point3D(location));
	}
	/**
	 * Returns true if the location is within the region
	 * @param location	the location to test
	 * @return
	 */
	public abstract boolean contains(Point3D location);
	/**
	 * Returns true if this region overlaps the input region
	 * @param region
	 * @return
	 */
	public abstract boolean Intersects(Region region);
	/**
	 * Returns true if this region overlaps the input region
	 * @param region
	 * @return
	 */
	public abstract boolean Intersects(NonCummulativeRegion region);
	/**
	 * Returns true if this region overlaps the input region
	 * @param region
	 * @return
	 * @throws IncorrectUnitOfTheCummulativeRegion 
	 */
	public abstract boolean Intersects(CummulativeRegion region);
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
	public abstract Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov);

}
