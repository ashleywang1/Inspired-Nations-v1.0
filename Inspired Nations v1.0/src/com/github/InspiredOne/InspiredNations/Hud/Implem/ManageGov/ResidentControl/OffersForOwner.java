package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
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
	public void Init() {
		for(PlayerID player:this.gov.getOwnerOffers()) {
			this.taboptions.add(player);
		}
	}

	@Override
	public String getHeader() {
		return gov.getOwnerPositionName() + " Offers";
	}

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new OffersForOwner(PDI, previous, gov);
	}

}
