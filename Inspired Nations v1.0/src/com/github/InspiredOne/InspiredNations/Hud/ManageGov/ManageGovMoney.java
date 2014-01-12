package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.PayNav;

public class ManageGovMoney extends OptionMenu {

	OwnerGov gov;
	public ManageGovMoney(PlayerData PDI, OwnerGov gov) {
		super(PDI);
		this.gov = gov;
	}

	@Override
	public String getPreOptionText() {
		return gov.getAccounts().getTotalMoney(PDI.getCurrency()).toString() + " " + PDI.getCurrency();
	}

	@Override
	public Menu PreviousMenu() {
		return new ManageGov(PDI, gov);
	}

	@Override
	public String getHeader() {
		return "Manage Gov Money";
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
		this.options.add(new PromptOption(this, "Pay", new PayNav(PDI, gov.getAccounts(), this)));
	}

}
