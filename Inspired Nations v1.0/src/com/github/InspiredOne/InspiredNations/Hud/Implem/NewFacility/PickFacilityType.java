package com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.GovernmentRegions;

public class PickFacilityType extends PassByOptionMenu {

	Class<? extends Facility> GovType;
	OwnerGov gov;
	
	
	public PickFacilityType(PlayerData PDI, OwnerGov gov, Class<? extends Facility> GovType) {
		super(PDI);
		this.GovType = GovType;
		this.gov = gov;
	}

	@Override
	public String getPreOptionText() {
		return "Pick the type of " + GovFactory.getGovInstance(GovType).getTypeName() + ".";
	}

	@Override
	public String getHeader() {
		return "Select " + GovFactory.getGovInstance(GovType).getTypeName() + " type.";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu getPreviousMenu() {
		GovFactory govf = new GovFactory(GovType);
		if(govf.getGov().getGeneralGovType().equals(GovType)) {
			return new GovernmentRegions(PDI, gov);
		}
		else {
			return new PickFacilityType(PDI, gov, (Class<? extends Facility>) ((Facility) govf.getGov()).getGeneralGovType());
		}
	}

	@Override
	public void init() {
		for(Class<? extends InspiredGov> gov:GovFactory.getGovInstance(GovType).getSelfGovs()) {
			GovFactory govf = new GovFactory(gov);
			((OwnerGov) govf.getGov()).getOwners().add(PDI.getName());
			if(govf.getGov().getSelfGovs().size() == 1) {
				if(govf.getGov().getSelfGovs().get(0).equals(govf.getGov().getClass())) {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickSuperGov(PDI, this.gov, govf)));
				}
				else {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickFacilityType(PDI, this.gov, ((Facility) govf.getGov()).getClass())));
				}
			}
			else {
				this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickFacilityType(PDI, this.gov, ((Facility) govf.getGov()).getClass())));
			}
		}
	}
}
