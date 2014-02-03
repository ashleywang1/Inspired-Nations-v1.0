package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class RequestsForSubject extends TabSelectOptionMenu<PlayerID> {

	OwnerSubjectGov gov;
	Menu previous;
	
	public RequestsForSubject(PlayerData PDI, Menu previous, OwnerSubjectGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new ManageCitizens(PDI, previous, gov);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(PlayerID player:this.gov.getSubjectRequests()) {
			this.taboptions.add(player);
		}
		this.options.add(new PromptOption(new RequestsForSubject(PDI, previous, gov), gov.getSubjectPositionName() + " Offers",
				new OffersForSubject(PDI, previous, gov)));
	}

	@Override
	public String getHeader() {
		return this.gov.getSubjectPositionName() + " Requests";
	}
	
}
