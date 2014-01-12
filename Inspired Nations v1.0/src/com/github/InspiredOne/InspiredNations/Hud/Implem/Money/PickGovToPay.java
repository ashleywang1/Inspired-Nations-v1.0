package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickGovGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class PickGovToPay extends PickGovGeneral {

	Payable accounts;
	
	public PickGovToPay(PlayerData PDI, Payable accountsFrom, Menu previous, Menu next) {
		super(PDI, previous, next);
		this.accounts = accountsFrom;
	}
	public PickGovToPay(PlayerData PDI, Payable accountsFrom, Menu previous, Menu next, Datable<InspiredGov> superGov) {
		super(PDI, previous, next, superGov);
		this.accounts = accountsFrom;
	}

	@Override
	public boolean check(InspiredGov gov) {
		return true;
	}

	@Override
	public void insertOptions() {
		if(this.getData().getAllSubGovsAndFacilitiesJustBelow().size() > 0) {
			this.options.add(new PromptOption(this, "Search Under", new PickGovToPay(PDI, accounts, this, this.next, this.getData())));
		}
		this.options.add(new PayAccountOption(PDI, this, "Pay " + this.getData().getTypeName() + " <amount>", accounts, this.getData().getAccounts()));
	}

}
