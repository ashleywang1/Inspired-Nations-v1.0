package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class PayNav extends PassByOptionMenu {

	Payable accounts;
	Menu back;
	/**
	 * 
	 * @param PDI
	 * @param accounts
	 * @param back	the menu to return to after doing all of the payment stuff.
	 */
	public PayNav(PlayerData PDI, Payable accounts, Menu back) {
		super(PDI);
		this.accounts = accounts;
		this.back = back;
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public Menu getPreviousMenu() {
		return back;
	}

	@Override
	public String getHeader() {
		return "Pay";
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Pay Player", new PayPlayer(PDI, accounts, back)));
		if(!InspiredNations.global.getData().getAllSubGovsAndFacilitiesJustBelow().isEmpty()) {
			this.options.add(new PromptOption(this, "Pay Government", new PickGovToPay(PDI, accounts, this, this)));
		}
	}

}
