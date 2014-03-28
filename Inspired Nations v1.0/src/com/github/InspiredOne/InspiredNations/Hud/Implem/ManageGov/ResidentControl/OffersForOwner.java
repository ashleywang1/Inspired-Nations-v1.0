package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.IgnoreOfferOption;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class OffersForOwner extends TabSelectOptionMenu<PlayerID> {

	Menu previous;
	OwnerGov gov;
	
	public OffersForOwner(PlayerData PDI, Menu previous, OwnerGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new RequestsForOwner(PDI, previous, gov);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return gov.getOwnerPositionName() + " Offers";
	}

	@Override
	public void addTabOptions() {
		for(PlayerID player:this.gov.getOwnerOffers()) {
			this.taboptions.add(player);
		}		
	}

	@Override
	public void addOptions() {
		if(taboptions.size() != 0) {
			this.options.add(new IgnoreOfferOption(this, "Remove Offer", this.getData(), this.gov.getOwnerOffers()));
		}
		this.options.add(new PromptOption(this, "Add Offer", new PickPlayerToOfferOwner(PDI, this, gov)));
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
