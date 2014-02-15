package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickPlayerGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PickPlayerToOfferOwner extends PickPlayerGeneral {

	OwnerGov gov;
	
	public PickPlayerToOfferOwner(PlayerData PDI, Menu previous, OwnerGov gov) {
		super(PDI, previous);
		this.gov = gov;
	}

	@Override
	public boolean check(PlayerID player) {
		if(gov.isOwner(player)) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void insertOptions() {
		if(this.taboptions.size() != 0) {
			this.options.add(new OfferOwnerOption(this, "Offer " + gov.getOwnerPositionName(), gov, this.getData()));
		}
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new PickPlayerToOfferOwner(PDI, previous, gov);
	}

	@Override
	public String getHeader() {
		return "Offer " + gov.getOwnerPositionName();
	}

}
