package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;


public class ServiceBusiness extends Business {

	public ServiceBusiness(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	public static String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		// TODO Auto-generated method stub
		return null;
	}

}
