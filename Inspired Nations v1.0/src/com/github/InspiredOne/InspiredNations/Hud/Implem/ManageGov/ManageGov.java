package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
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
	
	public static void addFacilityOptions(PlayerData PDI, OptionMenu menu, InspiredGov gov) {
		OptionMenu newman = new ManageGov(PDI, (OwnerGov) gov);
		if(gov.getGovFacilities().size() > 1) {
			menu.options.add(new PromptOption(newman, gov.getFacilityGroupName(), new GovernmentRegions(PDI, newman, gov)));
		}
		else {
			for(Class<? extends Facility> factype:gov.getGovFacilities()) {
				Facility facgov = GovFactory.getGovInstance(factype);
				if(gov.getFacilities().size() == 0 || !facgov.isUnique()) {
					menu.options.add(new PromptOption(newman, "New " + facgov.getTypeName(), new PickFacilityType<>(PDI, newman, gov, factype)));
				}
				if(gov.getFacilities().size() > 0) {
					menu.options.add(new PromptOption(newman, "Manage " + facgov.getTypeName(), new PickFacilityToManage<>(PDI, newman, gov, factype)));
				}
			}
		}
	}

	@Override
	public void addOptions() {
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Population", new ManageCitizens(PDI, new ManageGov(PDI, gov), gov)));
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Money", new ManageGovMoney(PDI, this, gov)));
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Claim Land", new PickClaimType(PDI,new ManageGov(PDI, gov), gov/*.getCommonGovObj().getData()*/)));
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Change Protection Level", new ProtectionLevels(PDI, new ManageGov(PDI, gov), gov)));
		if(gov instanceof OwnerSubjectGov) {
			this.options.add(new PromptOption(new ManageGov(PDI, gov), "Change Military Level", new MilitaryLevels(PDI, new ManageGov(PDI, gov), (OwnerSubjectGov) gov)));
		}
		if(gov.getRegion().getRegion().volume() != 0) {
			this.options.add(new UnclaimLandOption(new ManageGov(PDI, gov), "Unclaim Land", gov));
		}
		ManageGov.addFacilityOptions(PDI, this, gov);
	}

	@Override
	public void addActionManagers() {
		
	}

}
