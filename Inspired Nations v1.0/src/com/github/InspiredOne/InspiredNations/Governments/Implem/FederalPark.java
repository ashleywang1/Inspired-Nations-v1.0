package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.FederalParkLand;

public class FederalPark extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3146602156968003911L;

	public FederalPark() {
		super();
	}

	private static final String typeName = "Federal Park";

	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return Country.class;
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
		return FederalParkLand.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Country.class;
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return FederalParkLand.class;
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public String getFacilityGroupName() {
		return null;
	}

}
