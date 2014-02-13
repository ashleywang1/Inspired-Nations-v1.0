package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ManageAccounts extends TabSelectOptionMenu<Account> {

	Menu previous;
	AccountCollection accounts;
	
	public ManageAccounts(PlayerData PDI, Menu previous, AccountCollection accounts) {
		super(PDI);
		this.previous = previous;
		this.accounts = accounts;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}
	
	@Override
	public void Init() {
		for(Account account:accounts) {
			this.taboptions.add(account);
		}

		if(taboptions.size() > 0) {
			this.options.add(new ChangeTabOrderOption<>(new ManageAccounts(PDI, previous, accounts), "Change Account Order <+/->", PDI.getAccounts(), this.getData()));
			this.options.add(new PromptOption(getSelf(), "Manage " + this.getData().getName() + " account", new ManageAccount(PDI, previous, this.getData(), accounts)));
			this.options.add(new PromptOption(getSelf(), "Pay With " + this.getData().getName(), new PayNav(PDI, getSelf(), this.getData())));
			this.options.add(new PromptOption(getSelf(), "Manage Currencies In " +this.getData().getName(), new ManageCurrencies(PDI, previous,this.getData(), accounts)));
		}
		if(taboptions.size() > 1) {
			this.options.add(new RemoveAccountOption(new ManageAccounts(PDI, previous, accounts), "Remove Account", this.getData(), this.accounts));
		}
		this.options.add(new NewAccountOption(new ManageAccounts(PDI, previous, accounts), "New Account <Name>", accounts));
	}

	@Override
	public String getHeader() {
		return "Manage Accounts";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new ManageAccounts(PDI, previous, accounts);
	}

}
