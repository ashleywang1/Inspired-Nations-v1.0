package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PayPlayer extends TabSelectOptionMenu<PlayerID> {

	Payable accounts;
	Menu back;
	public PayPlayer(PlayerData PDI, Payable accounts, Menu back) {
		super(PDI);
		this.accounts = accounts;
		this.back = back;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new PayNav(PDI, accounts, back);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "Money: " + accounts.getTotalMoney(PDI.getCurrency()) + " " + PDI.getCurrency();
	}

	@Override
	public void Init() {
		for(PlayerID player:InspiredNations.playerdata.keySet()) {
			this.taboptions.add(player);
		}
		this.options.add(new PayAccountOption(PDI, this, "Pay Player <amount>", accounts, this.getData().getPDI().getAccounts()));
	}
	@Override
	public String getHeader() {
		return "Select a player to pay";
	}

}
