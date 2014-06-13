package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.Implem.ServiceBusinessLand;


public class ServiceBusiness extends Business {

	/**
	 * 
	 */
	private static final long serialVersionUID = 357732768193607382L;
	private static final String typeName = "Service Business";

	public ServiceBusiness() {
		super();
		this.setRegion(new ServiceBusinessLand());
	}
	
	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
		
	}
	
	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getGeneralGovType() {
		return Business.class;
	}
	
}
