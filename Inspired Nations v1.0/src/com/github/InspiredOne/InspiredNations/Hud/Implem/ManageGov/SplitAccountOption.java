package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class SplitAccountOption extends Option {

	InspiredGov gov;
	
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
		Debug.print("In Responce of split account option 1");
		if(gov.getAccounts().isLinked()) {
			Debug.print("In Responce of split account option 2");
			gov.setAccounts(new AccountCollection(""));
		}
		else {
			Debug.print("In Responce of split account option 3");
			menu.setError(MenuError.ACCOUNT_COLLECTION_NOT_LINKED());
		}
		Debug.print("In Responce of split account option 4");
		return menu;
	}

}
