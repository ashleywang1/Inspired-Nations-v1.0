package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility.NewFacility;

public class GovernmentRegions extends PassByOptionMenu {

	private InspiredGov gov;
	private Menu previous;
	public GovernmentRegions(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Government Regions";
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public void init() {

		for(Facility fac: gov.getFacilities()) {
			this.options.add(new PromptOption(this, "Manage " + fac.getTypeName(), new ManageFacility(PDI, previous, gov, fac)));
		}
		for(Class<? extends Facility> fac: gov.getGovFacilities()) {
			Facility facil = GovFactory.getGovInstance(fac);
			if(!facil.isUnique()) {
				this.options.add(new PromptOption(this, "New " + facil.getTypeName(), new NewFacility()))
			}
			else {
				boolean allowed = true;
				for(Facility facility:gov.getFacilities()) {
					if(facility.getClass().equals(fac)) {
						allowed = false;
					}
				}
				if(allowed) {
					
				}
			}
			
		}
			
		
	}


}
