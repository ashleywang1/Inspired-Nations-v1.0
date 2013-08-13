package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;

public class GlobalGov extends InspiredGov {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4539890228965981190L;
	private static final String typeName = "Global Government"; 

	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		return output;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(Country.class);
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
	public Class<? extends InspiredGov> getCommonEcon() {
		return this.getClass();
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
	}

}
