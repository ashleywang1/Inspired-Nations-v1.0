package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.LocalParkLand;

public class LocalPark extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1321780872694379634L;

	private static final String typeName = "Local Park";
	
	public LocalPark() {
		super();
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return Town.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {

		return Town.class;
	}
	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return LocalParkLand.class;
	}
	@Override
	public boolean isUnique() {
		return false;
	}
	@Override
	public String getFacilityGroupName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Option> getFunctionOptions() {
		List<Option> output = new ArrayList<Option>();
		return output;
	}

}
