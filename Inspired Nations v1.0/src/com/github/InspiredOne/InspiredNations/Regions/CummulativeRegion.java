package com.github.InspiredOne.InspiredNations.Regions;

import java.util.ArrayList;
import java.util.List;

public abstract class CummulativeRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8489617455582075095L;

	private List<NonCummulativeRegion> regions = new ArrayList<NonCummulativeRegion>();
	
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
		for(NonCummulativeRegion test:this.getRegions()) {
			if(!test.IsIn(region)) {
				return false;
			}
		}
		return true;
	}
	
	public List<NonCummulativeRegion> getRegions() {
		return regions;
	}

	@Override
	public double volume() {
		double output = 0;
		for(NonCummulativeRegion sum:regions) {
			output = output + sum.volume();
		}
		return output;
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
		for(NonCummulativeRegion test:this.getRegions()) {
			if(test.Intersects(region)) {
				return true;
			}
		}
		return false;
	}
}
