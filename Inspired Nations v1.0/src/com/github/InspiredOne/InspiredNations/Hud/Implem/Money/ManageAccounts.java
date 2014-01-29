package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Debug;
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
		this.options.add(new NewAccountOption(new ManageAccounts(PDI, previous, accounts), "New Account <Name>", PDI.getAccounts()));
		if(taboptions.size() > 0) {
			this.options.add(new PromptOption(this, "Pay With " + this.getData().getName(), new PayNav(PDI, this.getData(), new ManageAccounts(PDI, previous, accounts))));
			this.options.add(new RenameAccountOption(this, this.getData(), "Rename " + this.getData().getName() + " <Name>"));
			this.options.add(new ChangeAutoExchangeOption(new ManageAccounts(PDI, previous, accounts), "Toggle Auto-Exchange", this.getData()));
			this.options.add(new ChangeAccountOrderOption(new ManageAccounts(PDI, previous, accounts), "Change Account Order <+/->", PDI.getAccounts(), this.getData()));
			this.options.add(new PromptOption(new ManageAccounts(PDI, previous, accounts), "Manage Currencies In " +this.getData().getName(), new ManageCurrencies(PDI, previous, accounts, this.getData())));
		}
		
	}

	@Override
	public String getHeader() {
		return "Manage Accounts";
	}

}
