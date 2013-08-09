package com.github.InspiredOne.InspiredNations.Regions;

import java.util.List;

import org.bukkit.Location;

public abstract class InspiredRegion {

	private Region region;
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public abstract List<Class<? extends Region>> getAllowedForms();
	/**Returns all the regions that this InspiredRegion is allowed to be made of*/
	
	public abstract List<Class<? extends InspiredRegion>> getEncapsulatingRegions();
	/**Returns all the InspiredRegion that this InspiredRegion must be within*/
	
	public abstract List<Class<? extends InspiredRegion>> getAllowedOverlap();
	/**Returns all the InspiredRegions that this can overlap*/
	
	public abstract String getTypeName();
	/**Returns the type name to be used in menus*/
	
	public boolean contains(Location location) {
		if (region == null) {
			return false;
		}
		else {
			return region.contains(location);
		}
	}
}
