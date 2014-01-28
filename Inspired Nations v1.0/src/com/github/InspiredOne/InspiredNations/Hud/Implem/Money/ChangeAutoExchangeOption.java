package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class ChangeAutoExchangeOption extends Option {
	private Account account;
	public ChangeAutoExchangeOption(OptionMenu menu, String label,
			OptionUnavail reason) {
		super(menu, label, reason);
		
	}

	public ChangeAutoExchangeOption(OptionMenu menu, String label, Account account) {
		super(menu, label, ": " + account.isAutoExchange());
		this.account = account;
	}

	public ChangeAutoExchangeOption(OptionMenu menu, String label,
			String description) {
		super(menu, label, description);
	}

	@Override
	public Menu response(String input) {
		this.account.setAutoExchange(!this.account.isAutoExchange());
		return menu;
	}

}
