package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class ChangeAccountOrderOption extends Option {

	AccountCollection accounts;
	Account account;
	
	public ChangeAccountOrderOption(OptionMenu menu, String label,
			OptionUnavail reason, AccountCollection accounts, Account account) {
		super(menu, label, reason);
		this.accounts = accounts;
		this.account = account;
	}

	public ChangeAccountOrderOption(OptionMenu menu, String label, AccountCollection accounts, Account account) {
		super(menu, label);
		this.accounts = accounts;
		this.account = account;
	}

	public ChangeAccountOrderOption(OptionMenu menu, String label,
			String description, AccountCollection accounts, Account account) {
		super(menu, label, description);
		this.accounts = accounts;
		this.account = account;
	}

	@Override
	public Menu response(String input) {
		int position = accounts.indexOf(account);
		if(accounts.size() == 0) {
			return menu;
		}
		else {
			if(input.equalsIgnoreCase("-")) {
				accounts.add(position + 1, account);
				accounts.remove(position);
			}
			else if(input.equalsIgnoreCase("+"))  {
				accounts.add(position - 1, account);
				accounts.remove(position + 1);
			}
		}

		
		return menu;
	}

}
