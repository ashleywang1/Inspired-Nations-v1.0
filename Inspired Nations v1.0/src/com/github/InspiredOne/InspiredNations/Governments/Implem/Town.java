package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Subjects;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.TownLand;

public class Town extends Subjects {

	public Town(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Town";
	
	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(House.class);
		output.add(Business.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		// TODO Auto-generated method stub
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
		return TownLand.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(LocalPark.class);
		output.add(Bank.class);
		output.add(Prison.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}
}
