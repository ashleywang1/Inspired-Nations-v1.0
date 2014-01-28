package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.PrisonLand;

public class Prison extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3660969970048913227L;
	private static final String typeName = "Prison";

	public Prison() {
		super();
	}
	
	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return Town.class;
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
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return PrisonLand.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Town.class;
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return PrisonLand.class;
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getFacilityGroupName() {
		return null;
	}

}
