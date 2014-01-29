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
	ManageAccounts menu;
	
	public ChangeAccountOrderOption(ManageAccounts menu, String label,
			OptionUnavail reason, AccountCollection accounts, Account account) {
		super(menu, label, reason);
		this.accounts = accounts;
		this.account = account;
		this.menu = menu;
	}

	public ChangeAccountOrderOption(ManageAccounts menu, String label, AccountCollection accounts, Account account) {
		super(menu, label);
		this.accounts = accounts;
		this.account = account;
		this.menu = menu;
	}

	public ChangeAccountOrderOption(ManageAccounts menu, String label,
			String description, AccountCollection accounts, Account account) {
		super(menu, label, description);
		this.accounts = accounts;
		this.account = account;
		this.menu = menu;
	}

	@Override
	public Menu response(String input) {
		int position = accounts.indexOf(account);
		int size = accounts.size();
		if(size == 0) {
			return menu;
		}
		else {
			if(input.equalsIgnoreCase("-")) {
				int newpos = newPosition(position + 1, size);
				accounts.remove(position);
				accounts.add(newpos, account);
				menu.setTabcnt(newpos);
			}
			else if(input.equalsIgnoreCase("+"))  {
				int newpos = newPosition(position - 1, size);
				accounts.remove(position);
				accounts.add(newpos, account);
				menu.setTabcnt(newpos);
				
			}
		}

		
		return menu;
	}

	private int newPosition(int position, int size) {
		return (size + position) % size;
	}
}
