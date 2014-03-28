package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;

public class MilitaryLevels extends OptionMenu {

	Menu previous;
	OwnerSubjectGov gov;
	
	public MilitaryLevels(PlayerData PDI, Menu previous, OwnerSubjectGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
	}

	@Override
	public String getPreOptionText() {
		return "Military Level = " + gov.getMilitaryLevel();
	}

	@Override
	public String getHeader() {
		return "Military Levels";
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void addOptions() {
		this.options.add(new SetMilitaryLevelOption(this, "Set Military Level <level>", gov));
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
