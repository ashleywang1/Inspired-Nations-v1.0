package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

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
		this.options.add(new PromptOption(this, "Manage Money", new ManageGovMoney(PDI, gov)));
		this.options.add(new PromptOption(this, "Claim Land", new PickClaimType(PDI, gov, this.getSelf())));
		this.options.add(new PromptOption(this, "option 1", new MainHud(PDI)));
	}

}
