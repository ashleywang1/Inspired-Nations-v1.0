package com.github.InspiredOne.InspiredNations.Regions;

public class RegionFactory {

	Class<? extends Region> regionType;
	
	public RegionFactory(Class<? extends Region> regionType) {
		this.regionType = regionType;
	}
	
	public static <T extends Region> T getRegionInstance(Class<T> region) {
		try {
			return region.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}