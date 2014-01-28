package com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class PickFacilityType<T extends Facility> extends PassByOptionMenu {

	Class<T> GovType;
	OwnerGov gov;
	Menu previous;
	
	public PickFacilityType(PlayerData PDI, Menu previous, OwnerGov gov, Class<T> GovType) {
		super(PDI);
		this.GovType = GovType;
		this.gov = gov;
		this.previous = previous;
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
		GovFactory<T> govf = new GovFactory<T>(GovType);
		if(govf.getGov().getGeneralGovType().equals(GovType)) {
			return previous;
		}
		else {
			return new PickFacilityType<T>(PDI, previous, gov, (Class<T>) govf.getGov().getGeneralGovType());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		for(Class<? extends InspiredGov> gov:GovFactory.getGovInstance(GovType).getSelfGovs()) {
			GovFactory<T> govf = new GovFactory<T>((Class<T>) gov);
			if(govf.getGov().getSelfGovs().size() == 1) {
				if(govf.getGov().getSelfGovs().get(0).equals(govf.getGov().getClass())) {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickFacilityName(PDI, previous, this.gov, govf)));
				}
				else {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickFacilityType<T>(PDI, previous, this.gov, (Class<T>) (govf.getGov()).getClass())));
				}
			}
			else {
				this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickFacilityType<T>(PDI, previous, this.gov, (Class<T>) (govf.getGov()).getClass())));
			}
		}
	}
}
