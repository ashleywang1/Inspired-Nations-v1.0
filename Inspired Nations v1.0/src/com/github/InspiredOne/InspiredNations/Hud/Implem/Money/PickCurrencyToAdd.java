package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickCurrencyGeneral;

public class PickCurrencyToAdd extends PickCurrencyGeneral {

	Account account;
	
	public PickCurrencyToAdd(PlayerData PDI, Menu previous, Account account) {
		super(PDI, previous);
		this.account = account;
	}

	@Override
	public void insertOptions() {
		this.options.add(new AddCurrencyToAccountOption(this,"Add " + this.getData() + " To Your Account",account,this.getData()));
	}

}
