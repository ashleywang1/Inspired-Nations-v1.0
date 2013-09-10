package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.BankLand;

public class Bank extends Facility {

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
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Town.class;
	}

}
