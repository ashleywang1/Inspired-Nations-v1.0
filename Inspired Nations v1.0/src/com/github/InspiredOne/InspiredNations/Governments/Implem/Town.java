package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Governments.Subjects;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.TownLand;

public class Town extends Subjects {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3468445598611221739L;

	public Town() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Town";
	
	@Override
	public List<Class<? extends NoSubjects>> getSubGovs() {
		List<Class<? extends NoSubjects>> output = new ArrayList<Class<? extends NoSubjects>>();
		output.add(House.class);
		output.add(Business.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		return Country.class;
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
		return TownLand.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		output.add(LocalPark.class);
		output.add(Bank.class);
		output.add(Prison.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		return this.getClass();
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Country.class;
	}
}
