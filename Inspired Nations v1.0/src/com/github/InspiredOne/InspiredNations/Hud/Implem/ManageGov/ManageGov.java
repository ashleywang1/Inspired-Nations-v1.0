package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
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
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class ManageGov extends OptionMenu {

	OwnerGov gov;
	public ManageGov(PlayerData PDI, OwnerGov gov) {
		super(PDI);
		this.gov = gov;
		Debug.print("Inside Managegov");
	}

	@Override
	public String getPreOptionText() {
		Debug.print("Inside Managegov 2");

		return "";
	}

	@Override
	public Menu getPreviousMenu() {
		Debug.print("Inside Managegov 3");

		return new PickManageSuperGov(PDI, gov.getClass(), gov.getSuperGovObj());
	}

	@Override
	public String getHeader() {
		Debug.print("Inside Managegov 4");

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
		Debug.print("Inside Managegov 5");

		if(gov.getGovFacilities().size() > 1) {
			menu.options.add(new PromptOption(newman, gov.getFacilityGroupName(), new GovernmentRegions(PDI, newman, gov)));
		}
		else {
			Debug.print("Inside Managegov 6");

			for(Class<? extends Facility> factype:gov.getGovFacilities()) {
				Facility facgov = GovFactory.getGovInstance(factype);
				if(gov.getFacilities().size() == 0 || !facgov.isUnique()) {
					menu.options.add(new PromptOption(newman, "New " + facgov.getTypeName(), new PickFacilityType<>(PDI, newman, gov, factype)));
				}
				Debug.print("Inside Managegov 7");

				if(gov.getFacilities().size() > 0) {
					menu.options.add(new PromptOption(newman, "Manage " + facgov.getTypeName(), new PickFacilityToManage<>(PDI, newman, gov, factype)));
				}
				Debug.print("Inside Managegov 8");

			}
		}
		Debug.print("Inside Managegov 9");

	}

	@Override
	public void addOptions() {
		Debug.print("Inside Managegov 10");

		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Population", new ManageCitizens(PDI, new ManageGov(PDI, gov), gov),
				"(" + gov.getSubjects().size() + ")"));
		Debug.print("Inside Managegov 11");

		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Manage Money", new ManageGovMoney(PDI, this, gov),
				"(" + Tools.cut(gov.getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown))+" "+PDI.getCurrency()+")"));
		Debug.print("Inside Managegov 12");

		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Claim Land", new PickClaimType(PDI,new ManageGov(PDI, gov), gov/*.getCommonGovObj().getData()*/)));
		Debug.print("Inside Managegov 13");
		Debug.print(new ProtectionLevels(PDI, new ManageGov(PDI, gov), gov).PDI);
		Debug.print("Prot level: " + gov.getProtectionlevel());
		
		this.options.add(new PromptOption(new ManageGov(PDI, gov), "Change Protection Level", new ProtectionLevels(PDI, new ManageGov(PDI, gov), gov),
				"(" +gov.getProtectionlevel() +")"));
		
		Debug.print("Inside Managegov 14");

		if(gov instanceof OwnerSubjectGov) {
			this.options.add(new PromptOption(new ManageGov(PDI, gov), "Change Military Level", new MilitaryLevels(PDI, new ManageGov(PDI, gov), (OwnerSubjectGov) gov)
			,"(" + gov.getMilitaryLevel() + ")"));
		}
		Debug.print("Inside Managegov 15");

		if(gov.getRegion().getRegion().volume() != 0) {
			this.options.add(new UnclaimLandOption(new ManageGov(PDI, gov), "Unclaim Land", gov));
		}
		Debug.print("Inside Managegov 16");

		ManageGov.addFacilityOptions(PDI, this, gov);
	}

	@Override
	public void addActionManagers() {
		Debug.print("Inside Managegov 17");

	}

}
