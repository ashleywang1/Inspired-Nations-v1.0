package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickCurrencyGeneral;

public class PickCurrencyToAdd extends PickCurrencyGeneral {

	Account account;
	OptionMenu previous;
	
	public PickCurrencyToAdd(PlayerData PDI, OptionMenu previous, Account account) {
		super(PDI, previous);
		this.account = account;
		this.previous = previous;
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

	@Override
	public void addOptions() {
		if(this.getTabOptions().size() >= 2) {
			this.options.add(new AddCurrencyToAccountOption(this,"Add " + this.getData() + " To Your Account",account,this.getData()));
		}
		else if (this.getTabOptions().size() == 1) {
			this.options.add(new AddCurrencyToAccountOption(previous,"Add " + this.getData() + " To Your Account",account,this.getData()));
		}
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
