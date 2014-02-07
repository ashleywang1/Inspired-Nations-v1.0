package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class ManageAccount extends OptionMenu {

	private Menu previous;
	private Account account;
	private AccountCollection accounts;
	public ManageAccount(PlayerData PDI, Menu previous, Account account, AccountCollection accounts) {
		super(PDI);
		this.account = account;
		this.previous = previous;
		this.accounts = accounts;
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Manage " + account.getName();
	}

	@Override
	public Menu getPreviousMenu() {
		return new ManageAccounts(PDI, previous, accounts);
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		this.options.add(new ChangeAutoExchangeOption(new ManageAccount(PDI, previous, account, accounts), "Toggle Auto-Exchange", account));
		this.options.add(new PromptOption(this, "Pay With " + account.getName(), new PayNav(PDI, account, new ManageAccount(PDI, previous, account, accounts))));
		this.options.add(new RenameAccountOption(this, account, "Rename " + account.getName() + " <Name>"));
		this.options.add(new PromptOption(this, "Transfer Money", new PickAccount(PDI, this, accounts, account)));
		this.options.add(new RemoveAccountOption(new ManageAccounts(PDI, previous, this.accounts), "Remove Account", this.account, this.accounts));
		this.options.add(new PromptOption(this, "Share Account", new ShareAccountNav(PDI, previous, this.account)));
	}

}
