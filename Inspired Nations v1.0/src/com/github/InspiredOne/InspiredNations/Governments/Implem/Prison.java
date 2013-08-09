package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;

public class Prison extends InspiredGov {

	public Prison(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Prison";
	
	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getTypeName() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
