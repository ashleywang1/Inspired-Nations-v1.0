package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMilitaryLevelExecption;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class SetMilitaryLevelOption extends Option {

	OwnerSubjectGov gov;
	
	public SetMilitaryLevelOption(OptionMenu menu, String label,
			OptionUnavail reason, OwnerSubjectGov gov) {
		super(menu, label, reason);
		this.gov = gov;
	}

	public SetMilitaryLevelOption(OptionMenu menu, String label, OwnerSubjectGov gov) {
		super(menu, label);
		this.gov = gov;
	}

	public SetMilitaryLevelOption(OptionMenu menu, String label,
			String description, OwnerSubjectGov gov) {
		super(menu, label, description);
		this.gov = gov;
	}

	@Override
	public Menu response(String input) {
		try {
			this.gov.setMilitaryLevel(Integer.parseInt(input));
		} catch (NumberFormatException e) {
			this.menu.setError(MenuError.INVALID_NUMBER_INPUT());
		} catch (NegativeMilitaryLevelExecption e) {
			this.menu.setError(MenuError.NEGATIVE_MILITARY_LEVEL_NOT_ALLOWED());
		} catch (BalanceOutOfBoundsException e) {
			this.menu.setError(MenuError.NOT_ENOUGH_MONEY());
		}
		return menu;
	}

}
