package com.github.InspiredOne.InspiredNations.Regions;

import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public abstract class CummulativeRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8489617455582075095L;

	private HashSet<NonCummulativeRegion> regions = new HashSet<NonCummulativeRegion>();
	
	@Override
	public boolean contains(Point3D spot) {
		for(NonCummulativeRegion test:regions) {
			if(test.contains(spot)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean Intersects(Region region) {
		for(NonCummulativeRegion test:this.getRegions()) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		for(NonCummulativeRegion test:this.getRegions()) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Intersects(CummulativeRegion region) {
		return this.Intersects((Region) region);
	}
	
	@Override
	public boolean IsIn(Region region) {
		for(NonCummulativeRegion test:this.getRegions()) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		for(NonCummulativeRegion test:regions) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean IsIn(CummulativeRegion region) {
		return IsIn((Region) region);
	}
	
	public HashSet<NonCummulativeRegion> getRegions() {
		return regions;
	}

	@Override
	public int volume() {
		int output = 0;
		for(NonCummulativeRegion sum:regions) {
			output = output + sum.volume();
		}
		return output;
	}


}
