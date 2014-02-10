package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class SubjectRequests extends TabSelectOptionMenu<OwnerSubjectGov> {

	public SubjectRequests(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		return new SubjectOffers(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov instanceof OwnerSubjectGov) {
				if(((OwnerSubjectGov) gov).getSubjectRequests().contains(PDI.getPlayerID())) {
					this.taboptions.add((OwnerSubjectGov) gov);
				}
			}
		}
	}

	@Override
	public String getHeader() {
		return "Subject Requests";
	}

}
