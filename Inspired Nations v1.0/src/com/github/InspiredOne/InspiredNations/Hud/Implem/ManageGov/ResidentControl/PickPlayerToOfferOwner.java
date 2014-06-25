package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickPlayerGeneral;

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
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Offer " + gov.getOwnerPositionName();
	}

	@Override
	public void addOptions() {
		if(this.taboptions.size() != 0) {
			this.options.add(new OfferOwnerOption(this, "Offer " + gov.getOwnerPositionName(), gov, this.getData()));
		}
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
