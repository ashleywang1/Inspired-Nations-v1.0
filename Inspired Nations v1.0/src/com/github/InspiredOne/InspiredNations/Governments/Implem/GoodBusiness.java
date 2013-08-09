package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.GoodBusinessLand;


public class GoodBusiness extends Business {

	public GoodBusiness(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Good Business";
	
	public static String getTypeName() {
		return typeName;
	}

	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return GoodBusinessLand.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		// TODO Auto-generated method stub
		return null;
	}

}
