package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl.ManageCitizens;
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
		return "Manage " + gov.getDisplayName(PDI);
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
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Population", new ManageCitizens(PDI, new ManageGov(PDI, gov), gov)));
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Money", new ManageGovMoney(PDI, this, gov)));
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Claim Land", new PickClaimType(PDI, gov, new ManageGov(PDI, gov))));
		if(gov.getRegion().getRegion().volume() != 0) {
			this.options.add(new UnclaimLandOption(new ManageGov(PDI, gov), "Unclaim Land", gov));
		}
		ManageGov.addFacilityOptions(PDI, this, gov);
	}
	
	public static void addFacilityOptions(PlayerData PDI, OptionMenu menu, InspiredGov gov) {
		if(gov.getGovFacilities().size() > 1) {
			menu.getOptions().add(new PromptOption(menu, gov.getFacilityGroupName(), new GovernmentRegions(PDI, menu, gov)));
		}
		else {
			for(Class<? extends Facility> factype:gov.getGovFacilities()) {
				Facility facgov = GovFactory.getGovInstance(factype);
				if(gov.getFacilities().size() == 0 || !facgov.isUnique()) {
					menu.getOptions().add(new PromptOption(menu, "New " + facgov.getTypeName(), new PickFacilityType<>(PDI, menu, gov, factype)));
				}
				if(gov.getFacilities().size() > 0) {
					menu.getOptions().add(new PromptOption(menu, "Manage " + facgov.getTypeName(), new PickFacilityToManage<>(PDI, menu, gov, factype)));
				}
			}
		}
	}

}
