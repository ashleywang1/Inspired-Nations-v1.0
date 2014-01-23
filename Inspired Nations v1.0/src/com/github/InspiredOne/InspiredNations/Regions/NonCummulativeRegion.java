package com.github.InspiredOne.InspiredNations.Regions;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Cuboid;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.WorldID;

public abstract class NonCummulativeRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055862542770765137L;

	@Override
	public WorldID getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the bounding cuboid for the purposes of making selection
	 * verification speedy. 
	 * @return
	 */
	public abstract Cuboid getBoundingCuboid();
	
	/**
	 * Gets the world that this region resides in. Because it is non-cummulative
	 * the region exists entirely in one world.
	 * @return
	 */
	public abstract WorldID getWorld();

	public abstract boolean isIn(NonCummulativeRegion region);

	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return	
	 */
	@Override
	public final boolean IsIn(NonCummulativeRegion region) {
		Cuboid cube = this.getBoundingCuboid();
		if(region.contains(cube.getPointMax()) && region.contains(cube.getPointMin())) {
			return isIn(region);
		}
		else {
			return false;
		}
	}

	@Override
	public double volume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point3D location) {
		// TODO Auto-generated method stub
		return false;
	}

	protected abstract boolean intersects(Region region);

	@Override
	public final boolean Intersects(NonCummulativeRegion region) {
		
		Cuboid current = this.getBoundingCuboid();
		if(current.intersects(region)) {
			return this.intersects(region);
		}
		else {
			return false;
		}
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		// TODO Auto-generated method stub
		return null;
	}

}
