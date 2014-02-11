package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class SubjectOffers extends TabSelectOptionMenu<OwnerSubjectGov> {

	public SubjectOffers(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		return new PlayerCitizenship(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov instanceof OwnerSubjectGov) {
				if(((OwnerSubjectGov) gov).getSubjectOffers().contains(PDI.getPlayerID())) {
					this.taboptions.add((OwnerSubjectGov) gov);
				}
			}
		}
		this.options.add(new PromptOption(this, "Requests Citizenship", new SubjectRequests(PDI)));
	}

	@Override
	public String getHeader() {
		return "Offers For Citizenship";
	}

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new SubjectOffers(PDI);
	}

}
