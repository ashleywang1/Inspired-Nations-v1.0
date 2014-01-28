package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class NewAccountOption extends Option {

	AccountCollection accounts;
	public NewAccountOption(OptionMenu menu, String label, AccountCollection accounts) {
		super(menu, label);
		this.accounts = accounts;
	}

	@Override
	public Menu response(String input) {
		for(Account account:accounts){
			if(account.getName().equalsIgnoreCase(input)) {
				menu.setError(MenuError.ACCOUNT_NAME_ALREADY_TAKEN());
				return menu;
			}
		}
		accounts.add(new Account(input));
		return menu;
	}

}
