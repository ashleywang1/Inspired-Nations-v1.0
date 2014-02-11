package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class ManageFacility extends OptionMenu {

	private Facility fac;
	private OptionMenu previous;
	public ManageFacility(PlayerData PDI, OptionMenu previous, Facility fac) {
		super(PDI);
		this.fac = fac;
		this.previous = previous;
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getHeader() {
		return "Manage " + fac.getName();
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
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
		this.options.add(new PromptOption(this, "Manage Money", new ManageGovMoney(PDI, this, fac)));
		this.options.add(new PromptOption(this, "Claim Land", new PickClaimType(PDI, this.getSelf(), fac)));
		this.options.add(new RemoveFacilityOption((OptionMenu) previous, "Remove Facility", fac));	
	}

	@Override
	public OptionMenu getSelf() {
		return new ManageFacility(PDI, previous, fac);
	}

}
