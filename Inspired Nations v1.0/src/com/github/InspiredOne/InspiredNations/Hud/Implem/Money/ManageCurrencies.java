package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ManageCurrencies extends TabSelectOptionMenu<Currency> {

	Menu previous;
	AccountCollection accounts;
	Account account;

	public ManageCurrencies(PlayerData PDI, Menu previous, AccountCollection accounts, Account account) {
		super(PDI);
		this.previous = previous;
		this.account = account;
		this.accounts = accounts;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new ManageAccounts(PDI, previous, accounts);
	}

	@Override
	public String postTabListPreOptionsText() {
		
		return "";
	}

	@Override
	public void Init() {
		for(Currency curren:account.getMoney().keySet()) {
			this.taboptions.add(curren);
		}
	}

	@Override
	public String getHeader() {
		return "Manage Currency";
	}

}
