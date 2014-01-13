package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Regions.Region;

public class PickClaimType extends PassByOptionMenu {

	InspiredGov gov;
	Menu previous;
	
	public PickClaimType(PlayerData PDI, InspiredGov gov, Menu previous) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public String getPreOptionText() {
		
		return null;
	}

	@Override
	public Menu PreviousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		for(Class<? extends Region> regiontype:gov.getRegion().getAllowedForms()) {
			
		}

	}

}
