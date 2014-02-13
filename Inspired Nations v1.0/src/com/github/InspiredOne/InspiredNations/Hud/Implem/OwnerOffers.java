package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class OwnerOffers extends TabSelectOptionMenu<OwnerGov> {

	public OwnerOffers(PlayerData PDI) {
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
			if(gov instanceof OwnerGov) {
				if(((OwnerGov) gov).getOwnerOffers().contains(PDI.getPlayerID())) {
					this.taboptions.add((OwnerGov) gov);
				}
			}
		}
		this.options.add(new PromptOption(this, "Request Ownership", new OwnerRequests(PDI)));
	}

	@Override
	public String getHeader() {
		return "Offers For Ownership";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new OwnerOffers(PDI);
	}

}
