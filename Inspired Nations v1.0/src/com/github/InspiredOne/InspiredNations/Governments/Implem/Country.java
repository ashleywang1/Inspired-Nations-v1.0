package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Subjects;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.CountryLand;

public class Country extends Subjects {

	public Country(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Country";
	
	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(Town.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		return null;
	}

	public static String getTypeName() {
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
		return CountryLand.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(FederalPark.class);
		
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
