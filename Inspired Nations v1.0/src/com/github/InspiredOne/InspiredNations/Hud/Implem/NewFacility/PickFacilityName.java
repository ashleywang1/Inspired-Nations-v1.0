package com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickFacilityName extends InputMenu {

	InspiredGov gov;
	GovFactory<? extends Facility> Govf;
	Menu previous;
	
	public PickFacilityName(PlayerData PDI, Menu previous, InspiredGov gov, GovFactory<? extends Facility> Govf) {
		super(PDI);
		this.gov = gov;
		this.Govf = Govf;
		this.previous = previous;
	}

	@Override
	public String validate(String input) {
		
		boolean allowed = true;
		Debug.print(InspiredNations.regiondata.get(Govf.getGov().getClass()).size());
		
		for(InspiredGov gov: InspiredNations.regiondata.get(Govf.getGov().getClass())) {
			if(gov.getSuperGovObj().equals(Govf.getGov().getSuperGovObj()) && gov.getName().equalsIgnoreCase(input)) {
				allowed = false;
			}
		}
		if(allowed) {
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NAME_ALREADY_TAKEN(Govf.getGov().getClass(), this.getPlayerData());
		}
	}

	@Override
	public void useInput(String input) {
		Govf = Govf.withName(input);
		Govf.registerGov();
		this.gov.getFacilities().add(Govf.getGov());
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
		return previous;
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
	public void addTabOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
