package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PickAccount extends TabSelectOptionMenu<Account> {

	AccountCollection accounts;
	Payable accountFrom;
	Menu previous;
	public PickAccount(PlayerData PDI, Menu previous, AccountCollection collection, Payable accountFrom) {
		super(PDI);
		this.accounts = collection;
		this.previous = previous;
		this.accountFrom = accountFrom;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return TextColor.LABEL + "Money: " + TextColor.VALUE + 
				Tools.cut(accounts.getTotalMoney(PDI.getCurrency())) + " " + PDI.getCurrency();
	}

	@Override
	public void Init() {
		for(Account account:accounts) {
			this.taboptions.add(account);
		}
		this.options.add(new PromptOption(this, "Pick Currency Account", new PickCurrencyCollection(PDI, this, this.getData(), accountFrom)));
		this.options.add(new PayAccountOption(PDI, this, "Transfer Money <amount>", accountFrom, this.getData()));
	}

	@Override
	public String getHeader() {
		return "Pick Account To Transfer Money Into";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new PickAccount(PDI, previous, accounts, accountFrom);
	}

}
