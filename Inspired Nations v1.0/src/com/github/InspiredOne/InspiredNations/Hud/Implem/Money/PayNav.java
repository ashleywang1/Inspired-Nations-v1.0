package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.util.List;
import java.util.Set;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;

public class PayNav extends PassByOptionMenu {

	Set<Account> accounts;
	public PayNav(PlayerData PDI, Set<Account> accounts) {
		super(PDI);
		this.accounts = accounts;
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public Menu PreviousMenu() {
		return new ManageMoney(PDI);
	}

	@Override
	public String getHeader() {
		return "Pay";
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Pay Player", new PayPlayer(PDI, accounts)));
		this.options.add(new PromptOption(this, "Pay Government", new PayGov(PDI, accounts)));
	}

}
