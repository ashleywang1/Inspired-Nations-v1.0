package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ManageAccounts extends TabSelectOptionMenu<Account> {

	public ManageAccounts(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu getPreviousPrompt() {
		return new ManageMoney(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}
	
	@Override
	public void Init() {
		Debug.print("Inside Init of ManageAccounts");
		for(Account account:PDI.getAccounts()) {
			this.taboptions.add(account);
		}
		this.options.add(new NewAccountOption(new ManageAccounts(PDI), "New Account <Name>", PDI.getAccounts()));
		if(taboptions.size() > 0) {
			this.options.add(new RenameAccountOption(this, this.getData(), "Rename " + this.getData().getName() + " <Name>"));
			this.options.add(new ChangeAutoExchangeOption(new ManageAccounts(PDI), "Toggle Auto-Exchange", this.getData()));
			this.options.add(new ChangeAccountOrderOption(this, "Change Account Order <+/->", PDI.getAccounts(), this.getData()));
		}
	}

	@Override
	public String getHeader() {
		return "Manage Accounts";
	}

}
