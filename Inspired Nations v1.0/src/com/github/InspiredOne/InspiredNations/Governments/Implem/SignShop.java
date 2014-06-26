package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.SignShopLand;

public class SignShop extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4766098780512013682L;

	public SignShop() {

	}
	

	@Override
	public void setFunctionOptions(PlayerData PDI, OptionMenu menu) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return SignShopLand.class;
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
	public Class<? extends OwnerGov> getSuperGov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(SignShop.class);
		return output;
	}

	@Override
	public String getTypeName() {
		return "Sign Shop";
	}

	@Override
	public String getFacilityGroupName() {
		return "";
	}
}
