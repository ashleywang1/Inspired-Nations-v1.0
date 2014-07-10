package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class LeaveGovOption extends Option {

	OwnerGov gov;
	PlayerData PDI;
	public LeaveGovOption(OptionMenu menu, String label, OptionUnavail reason, OwnerGov gov) {
		super(menu, label, reason);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, OwnerGov gov) {
		super(menu, label);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, String description, OwnerGov gov) {
		super(menu, label, description);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	@Override
	public Menu response(String input) {
		gov.removePlayer(PDI);
		return menu;
	}

}
