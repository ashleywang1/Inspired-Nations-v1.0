package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class SplitAccountOption extends Option {

	InspiredGov gov;
	PlayerData PDI;
	
	public SplitAccountOption(OptionMenu menu, String label,
			OptionUnavail reason, InspiredGov gov) {
		super(menu, label, reason);
		this.gov = gov;
	}

	public SplitAccountOption(OptionMenu menu, String label, InspiredGov gov) {
		super(menu, label);
		this.gov = gov;
	}

	public SplitAccountOption(OptionMenu menu, String label, String description, InspiredGov gov) {
		super(menu, label, description);
		this.gov = gov;
	}

	@Override
	public Menu response(String input) {
		PDI = menu.PDI;
		if(gov.getAccounts().isLinked()) {
			gov.splitAccount(PDI, new ArrayList<PlayerID>(), new AccountCollection(gov.getName()));
		}
		else {
			menu.setError(MenuError.ACCOUNT_COLLECTION_NOT_LINKED(menu.PDI));
		}
		return menu;
	}

}
