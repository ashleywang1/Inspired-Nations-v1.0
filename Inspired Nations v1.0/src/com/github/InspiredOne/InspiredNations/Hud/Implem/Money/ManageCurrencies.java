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

	public ManageCurrencies(PlayerData PDI, Menu previous, Account account, AccountCollection accounts) {
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
			this.options.add(new PromptOption(getSelf(), "Pay with " + this.getData().getName(), new PayNav(PDI, getSelf(), this.getData())));
			this.options.add(new ChangeTabOrderOption<>(getSelf(), "Change Currency Order <+/->", account.getMoney(), this.getData()));
			this.options.add(new PromptOption(this, "Transfer " + this.getData().getCurrency(), new PickAccount(PDI, this, accounts, account)));
			if(this.taboptions.size() > 1) {
				this.options.add(new RemoveCurrencyOption(getSelf(), "Remove " + this.getData().getCurrency(), account, this.getData()));
			}
		}
		this.options.add(new PromptOption(this, "Add Currency", new PickCurrencyToAdd(PDI, getSelf(), account)));
	}

	@Override
	public String getHeader() {
		return "Manage Currency";
	}

	@Override
	public TabSelectOptionMenu<CurrencyAccount> getSelf() {
		return new ManageCurrencies(PDI, previous, account, accounts);
	}

}
