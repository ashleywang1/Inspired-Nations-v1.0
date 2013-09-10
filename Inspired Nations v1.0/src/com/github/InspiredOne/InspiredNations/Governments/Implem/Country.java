package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Governments.Subjects;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.CountryLand;

public class Country extends Subjects {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4242189402669618103L;

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Country";
	
	@Override
	public List<Class<? extends NoSubjects>> getSubGovs() {
		List<Class<? extends NoSubjects>> output = new ArrayList<Class<? extends NoSubjects>>();
		output.add(Town.class);
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		return GlobalGov.class;
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
		return CountryLand.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		output.add(FederalPark.class);
		
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		return this.getClass();
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return this.getClass();
	}

}
