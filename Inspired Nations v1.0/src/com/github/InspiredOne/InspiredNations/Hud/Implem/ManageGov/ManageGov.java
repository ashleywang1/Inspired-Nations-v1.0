package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.NewFacility.PickFacilityType;

public class ManageGov extends OptionMenu {

	OwnerGov gov;
	public ManageGov(PlayerData PDI, OwnerGov gov) {
		super(PDI);
		this.gov = gov;

	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public Menu getPreviousMenu() {
		return new PickManageSuperGov(PDI, gov.getClass(), gov.getSuperGovObj());
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Manage Money", new ManageGovMoney(PDI, this, gov)));
		this.options.add(new PromptOption(this, "Claim Land", new PickClaimType(PDI, gov, this.getSelf())));
		if(gov.getGovFacilities().size() > 1) {
			this.options.add(new PromptOption(this, gov.getFacilityGroupName(), new GovernmentRegions(PDI, gov)));
		}
		else {
			for(Class<? extends Facility> factype:gov.getGovFacilities()) {
				Facility facgov = GovFactory.getGovInstance(factype);
				if(gov.getFacilities().size() == 0 || !facgov.isUnique()) {
					this.options.add(new PromptOption(this, "New " + facgov.getTypeName(), new PickFacilityType<>(PDI, this, gov, factype)));
				}
				if(gov.getFacilities().size() > 0) {
					this.options.add(new PromptOption(this, "Manage " + facgov.getTypeName(), new PickFacilityToManage<>(PDI, this, gov, factype)));
				}
			}
		}
	}

}
