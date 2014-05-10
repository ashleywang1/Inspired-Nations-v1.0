package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.BankLand;

public class Bank extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274087416352050894L;

	public Bank() {
		super();
	}

	private static final String typeName = "Bank";

	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return Town.class;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
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

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return BankLand.class;
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public String getFacilityGroupName() {
		return "";
	}
	@Override
	public void setFunctionOptions(PlayerData PDI, OptionMenu menu) {
		List<Option> output = new ArrayList<Option>();
	}

}
