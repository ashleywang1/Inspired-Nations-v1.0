package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class Applications extends TabSelectOptionMenu<OwnerGov> {

	public Applications(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
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
		for(InspiredGov gov: InspiredNations.regiondata) {
			if((gov instanceof OwnerGov) && gov.getSubjects().contains(PDI.getPlayerID())) {
				this.taboptions.add((OwnerGov) gov);
			}
		}
		OwnerGov gov = this.getData();
		//if(gov.getOwnerOffers())
		if(gov instanceof OwnerSubjectGov) {
			//
		}
	}

	@Override
	public String getHeader() {
		return "Applications";
	}



}
