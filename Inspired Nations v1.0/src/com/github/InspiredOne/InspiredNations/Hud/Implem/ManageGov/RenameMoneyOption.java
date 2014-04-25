package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class RenameMoneyOption extends Option {

	InspiredGov gov;
	
	public RenameMoneyOption(OptionMenu menu, String label, OptionUnavail reason, InspiredGov gov) {
		super(menu, label, reason);
		this.gov = gov;
	}

	public RenameMoneyOption(OptionMenu menu, String label, InspiredGov gov) {
		super(menu, label);
		this.gov = gov;
	}

	public RenameMoneyOption(OptionMenu menu, String label, String description, InspiredGov gov) {
		super(menu, label, description);
		this.gov = gov;
	}

	@Override
	public Menu response(String input) {
		try {
			this.gov.getCurrency().setName(input);
		} catch (NameAlreadyTakenException e) {
			menu.setError(MenuError.MONEY_NAME_ALREADY_TAKEN());
		}
		
		return menu;
	}

}
