package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ChangeTaxRateMenu.TaxRateOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ChangeTaxOption extends Option {

	OwnerGov gov;
	TaxRateOption option;
	public ChangeTaxOption(OptionMenu menu, String label, TaxRateOption option, OwnerGov gov) {
		super(menu, label);
		this.option = option;
		this.gov = gov;
	}

	@Override
	public Menu response(String input) {
		try {
			Double newrate = Double.parseDouble(input);
			gov.getTaxrates().put(option.gov, newrate);
		}
		catch (Exception ex) {
			menu.setError(MenuError.INVALID_NUMBER_INPUT(menu.PDI));
		}
		return menu;
	}

}
