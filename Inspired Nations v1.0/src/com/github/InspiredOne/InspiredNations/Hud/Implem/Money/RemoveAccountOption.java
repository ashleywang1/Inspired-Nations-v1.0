package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class RemoveAccountOption extends Option {

	Account account;
	AccountCollection superAcc;
	
	public RemoveAccountOption(OptionMenu menu, String label, Account account, AccountCollection superAcc,
			OptionUnavail reason) {
		super(menu, label, reason);
		this.account = account;
		this.superAcc = superAcc;
	}

	public RemoveAccountOption(OptionMenu menu, String label, Account account, AccountCollection superAcc) {
		super(menu, label);
		this.account = account;	
		this.superAcc = superAcc;
	}

	public RemoveAccountOption(OptionMenu menu, String label, Account account, AccountCollection superAcc, String description) {
		super(menu, label, description);
		this.account = account;
		this.superAcc = superAcc;
	}

	@Override
	public Menu response(String input) {
		superAcc.remove(account);
		try {
			account.transferMoney(account.getTotalMoney(Currency.DEFAULT), Currency.DEFAULT, superAcc);
		} catch (BalanceOutOfBoundsException e) {
		} catch (NegativeMoneyTransferException e) {
		}
		return menu;
	}

}
