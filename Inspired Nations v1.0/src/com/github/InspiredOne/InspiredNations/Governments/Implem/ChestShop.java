package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.ShopLand;

public class ChestShop extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2528500041859238661L;

	public ChestShop() {
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return ShopLand.class;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Country.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(ChestShop.class);
		return output;
	}

	@Override
	public String getTypeName() {
		return "Chest Shop";
	}

	@Override
	public String getFacilityGroupName() {
		return "";
	}

	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return GoodBusiness.class;
	}
}
