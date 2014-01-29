package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Economy.CurrencyAccount;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ManageCurrencies extends TabSelectOptionMenu<CurrencyAccount> {

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
		for(CurrencyAccount curren:account.getMoney()) {
			this.taboptions.add(curren);
		}
		if(this.taboptions.size() > 0) {
			this.options.add(new PromptOption(this, "Pay with " + this.getData().getName(), new PayNav(PDI, this.getData(), this)));
			this.options.add(new ChangeTabOrderOption<>(new ManageCurrencies(PDI, previous, accounts, account), "Change Currency Order <+/->", account.getMoney(), this.getData()));
		}
	}

	@Override
	public String getHeader() {
		return "Manage Currency";
	}

}
