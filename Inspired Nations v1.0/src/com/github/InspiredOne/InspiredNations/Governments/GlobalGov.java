package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;

public class GlobalGov extends InspiredGov {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4539890228965981190L;

	public GlobalGov() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paySuper(BigDecimal amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		// TODO Auto-generated method stub
		return null;
	}

}
