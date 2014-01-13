package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.HouseLand;

public class House extends OwnerGov {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1683829601317933069L;
	private static final String typeName = "House";
	
	public House() {
		super();
	}
	
	@Override
	public List<Class<? extends OwnerGov>> getSubGovs() {
		List<Class<? extends OwnerGov>> output = new ArrayList<Class<? extends OwnerGov>>();
		return output;
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
	public void paySuper(BigDecimal amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
	}

	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return HouseLand.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		return Country.class;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Country.class;
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return HouseLand.class;
	}

}
