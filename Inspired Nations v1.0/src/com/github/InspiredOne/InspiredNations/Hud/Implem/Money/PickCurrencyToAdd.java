package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickCurrencyGeneral;

public class PickCurrencyToAdd extends PickCurrencyGeneral {

	Account account;
	Menu previous;
	
	public PickCurrencyToAdd(PlayerData PDI, Menu previous, Account account) {
		super(PDI, previous);
		this.account = account;
		this.previous = previous;
	}

	@Override
	public void insertOptions() {
		this.options.add(new AddCurrencyToAccountOption(this,"Add " + this.getData() + " To Your Account",account,this.getData()));
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new PickCurrencyToAdd(PDI, previous, account);
	}

	@Override
	public boolean check(Currency curren) {
		if(account.containsCurrency(curren)) {
			return false;
		}
		else {
			return true;
		}
	}

}
