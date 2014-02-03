package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class RequestsForOwner extends TabSelectOptionMenu<PlayerID> {

	OwnerGov gov;
	Menu previous;
	
	public RequestsForOwner(PlayerData PDI, Menu previous, OwnerGov gov) {
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
		for(PlayerID player:this.gov.getOwnerRequest()) {
			this.taboptions.add(player);
		}
		this.options.add(new PromptOption(new RequestsForOwner(PDI, previous, gov), gov.getOwnerPositionName() + " Offers",
				new OffersForOwner(PDI, previous, gov)));
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return gov.getOwnerPositionName() + " Offers";
	}

}
