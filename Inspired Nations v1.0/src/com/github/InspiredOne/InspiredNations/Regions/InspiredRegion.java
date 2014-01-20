package com.github.InspiredOne.InspiredNations.Regions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
/**
 * This is the interface between the social construct of a government and the physical construct
 * of the world. 
 * @author Jedidiah Phillips
 *
 */
public abstract class InspiredRegion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3476603574063579787L;
	
	private Region region;
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	/**
	 * Returns all the SelectionModes that this InspiredRegion is allowed to be made of
	 * @return
	 */
	public abstract List<Class<? extends SelectionMode>> getAllowedForms();
	
	/**
	 * Gets all the regions that this region must be completely within. Only considers the immediate
	 * regions that encapsulate it. Not the region that encapsulates the encapsulating region.
	 * @return
	 */
	public abstract List<Class<? extends InspiredRegion>> getEncapsulatingRegions();

	/**
	 * Returns all the InspiredRegions that this can overlap
	 * @return
	 */
	protected abstract List<Class<? extends InspiredRegion>> getAllowedOverlap();
	/**
	 * Returns the type name to be used in menus
	 * @return
	 */
	public abstract String getTypeName();
	/**
	 * Gets all the overlap regions for this region that includes all the Encapsulating regions
	 * and the encapsulating regions overlap regions... and so on.
	 * @return
	 */
	public List<Class<? extends InspiredRegion>> getOverlap() {
		List<Class<? extends InspiredRegion>> output = new ArrayList<Class<? extends InspiredRegion>>();
		output.addAll(this.getAllowedOverlap());
		output.addAll(this.getEncapsulatingRegions());
		try {
			for(Class<? extends InspiredRegion> region:this.getEncapsulatingRegions()) {
				output.addAll(region.newInstance().getOverlap());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return output;
	}
	
	public boolean contains(Location location) {
		if (region == null) {
			return false;
		}
		else {
			return region.contains(location);
		}
	}
}
