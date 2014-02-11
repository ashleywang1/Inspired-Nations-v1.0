package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
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
		this.options.add(new PromptOption(this, gov.getOwnerPositionName() + " Requests and Offers", new RequestsForOwner(PDI, previous, gov)));
		if(gov instanceof OwnerSubjectGov) {
			this.options.add(new PromptOption(this, ((OwnerSubjectGov) gov).getSubjectPositionName() + " Requests and Offers", new RequestsForSubject(PDI, previous, (OwnerSubjectGov) gov)));
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

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new ManageCitizens(PDI, previous, gov);
	}
}
