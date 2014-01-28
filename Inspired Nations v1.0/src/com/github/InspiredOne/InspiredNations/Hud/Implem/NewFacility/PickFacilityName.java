package com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ManageGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickFacilityName extends InputMenu {

	OwnerGov gov;
	GovFactory<? extends Facility> Govf;
	Menu previous;
	
	public PickFacilityName(PlayerData PDI, Menu previous, OwnerGov gov, GovFactory<? extends Facility> Govf) {
		super(PDI);
		this.gov = gov;
		this.Govf = Govf;
		this.previous = previous;
	}

	@Override
	public String validate(String input) {
		
		boolean allowed = true;
		
		for(InspiredGov gov: InspiredNations.regiondata.get(Govf.getGov().getClass())) {
			if(gov.getSuperGovObj().equals(Govf.getGov().getSuperGovObj()) && gov.getName().equalsIgnoreCase(input)) {
				allowed = false;
			}
		}
		if(allowed) {
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NAME_ALREADY_TAKEN(Govf.getGov().getClass());
		}
	}

	@Override
	public void useInput(String input) {
		Govf = Govf.withName(input).withSuperGov(gov);
		this.gov.getFacilities().add(Govf.getGov());
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getHeader() {
		return "Type Name";
	}

	@Override
	public String getInstructions() {
		return "Type the name that you would like to use for this " + Govf.getGov().getTypeName() + ".";
	}

	@Override
	public Menu nextMenu() {
		return new ManageGov(PDI, gov);
	}

	@Override
	public Menu getPreviousMenu() {
		return new PickFacilityType<>(PDI, previous, gov, Govf.getGov().getClass());
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public void Init() {
		
	}

}
