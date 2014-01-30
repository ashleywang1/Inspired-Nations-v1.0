package com.github.InspiredOne.InspiredNations.Regions;

import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public abstract class CummulativeRegion<T extends NonCummulativeRegion> extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8489617455582075095L;

	private HashSet<T> regions = new HashSet<T>();
	
	@Override
	public boolean contains(Point3D spot) {
		for(T test:regions) {
			if(test.contains(spot)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean Intersects(Region region) {
		for(T test:regions) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		for(T test:this.getRegions()) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Intersects(CummulativeRegion<?> region) {
		for(T test:this.getRegions()) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean IsIn(Region region) {
		Debug.print("in CummulativeRegion.IsIn(Region))");
		for(T test:this.getRegions()) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		Debug.print("in CummulativeRegion.IsIn(NonCummulativeRegion))");
		for(NonCummulativeRegion test:this.getRegions()) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean IsIn(CummulativeRegion<?> region) {
		Debug.print("in CummulativeRegion.IsIn(CummulativeRegion)");
		for(NonCummulativeRegion test:this.getRegions()) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	
	public HashSet<T> getRegions() {
		return regions;
	}

	@Override
	public int volume() {
		int output = 0;
		for(T sum:regions) {
			output = output + sum.volume();
		}
		return output;
	}
	
	public abstract <E extends InspiredGov> Menu getUnclaimMenu(PlayerData PDI, Menu previous, E gov);


}
