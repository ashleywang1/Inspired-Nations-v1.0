package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.FederalParkLand;

public class FederalPark extends InspiredGov {

	public FederalPark(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Federal Park";
	
	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		return Country.class;
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
		return FederalParkLand.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
