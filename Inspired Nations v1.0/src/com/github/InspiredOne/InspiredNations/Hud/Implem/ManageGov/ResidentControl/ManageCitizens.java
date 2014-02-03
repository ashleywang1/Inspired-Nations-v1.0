package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class ManageCitizens extends TabSelectOptionMenu<PlayerID> {
	OwnerGov gov;
	Menu previous;
	public ManageCitizens(PlayerData PDI, Menu previous, OwnerGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(PlayerID subject:gov.getSubjects()) {
			this.taboptions.add(subject);
		}
		
		if(gov instanceof OwnerSubjectGov) {
			
		}
	}

	@Override
	public String getHeader() {
		if(gov instanceof OwnerSubjectGov) {
			return "Manage " + ((OwnerSubjectGov) gov).getSubjectPositionName();
		}
		else {
			return "Manage " + gov.getOwnerPositionName();
		}

	}
}
