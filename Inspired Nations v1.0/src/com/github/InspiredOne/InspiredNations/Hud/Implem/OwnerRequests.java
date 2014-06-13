package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class OwnerRequests extends TabSelectOptionMenu<OwnerGov> {

	public OwnerRequests(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		return new OwnerOffers(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Requests for Ownership";
	}

	@Override
	public void addTabOptions() {
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov instanceof OwnerGov) {
				if(((OwnerGov) gov).getOwnerRequests().contains(PDI.getPlayerID())) {
					this.taboptions.add((OwnerGov) gov);
				}
			}
		}
	}

	@Override
	public void addOptions() {
		if(this.taboptions.size() != 0) {
			this.options.add(new IgnoreOfferOption(this, "Remove Request",PDI.getPlayerID(), this.getData().getOwnerRequests()));
		}
		this.options.add(new PromptOption(this, "Add Request", new PickGovToRequestOwner(PDI, this, this)));
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
