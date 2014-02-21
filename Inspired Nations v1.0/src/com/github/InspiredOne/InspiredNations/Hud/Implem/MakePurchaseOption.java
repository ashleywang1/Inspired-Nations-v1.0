package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class MakePurchaseOption extends Option {
	Sellable item;
	public MakePurchaseOption(OptionMenu menu, String label,
			OptionUnavail reason, Sellable item) {
		super(menu, label, reason);
		this.item = item;
	}

	public MakePurchaseOption(OptionMenu menu, String label, Sellable item) {
		super(menu, label);
		this.item = item;
	}

	public MakePurchaseOption(OptionMenu menu, String label, String description, Sellable item) {
		super(menu, label, description);
		this.item = item;
	}

	@Override
	public Menu response(String input) {
		this.item.transferOwnership(menu.PDI.getPlayerID());
		return menu;
	}

}
