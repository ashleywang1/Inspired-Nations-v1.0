package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Economy.CurrencyAccount;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PickCurrencyCollection extends TabSelectOptionMenu<CurrencyAccount> {

	Menu previous;
	Account account;
	Payable accountFrom;
	public PickCurrencyCollection(PlayerData PDI, Menu previous, Account account, Payable accountFrom) {
		super(PDI);
		this.previous = previous;
		this.account = account;
		this.accountFrom = accountFrom;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return TextColor.LABEL + "Money: " + TextColor.VALUE + 
				Tools.cut(accountFrom.getTotalMoney(PDI.getCurrency())) + " " + PDI.getCurrency();
	}

	@Override
	public void Init() {
		for(CurrencyAccount curren:account.getMoney()) {
			this.taboptions.add(curren);
		}
		this.options.add(new PayAccountOption(PDI, new PickCurrencyCollection(PDI, previous, account, accountFrom), "Transfer Funds <amount>", accountFrom, this.getData()));
	}

	@Override
	public String getHeader() {
		return "Pick Currency To Transfer Money Into";
	}

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new PickCurrencyCollection(PDI, previous, account, accountFrom);
	}
	
}
