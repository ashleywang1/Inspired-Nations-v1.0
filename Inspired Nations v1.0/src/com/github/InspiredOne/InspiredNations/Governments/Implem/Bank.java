package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.BankLand;

public class Bank extends InspiredGov {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274087416352050894L;

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String typeName = "Bank";
	
	@Override
	public List<Class<? extends NoSubjects>> getSubGovs() {
		List<Class<? extends NoSubjects>> output = new ArrayList<Class<? extends NoSubjects>>();
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
		return BankLand.class;
	}

	@Override
	public List<Class<? extends InspiredGov>> getGovFacilities() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Town.class;
	}

}
