package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;

public class ToggleTimerOption extends Option {

	public ToggleTimerOption(OptionMenu menu, String label) {
		super(menu, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu response(String input) {
		InspiredNations.taxTimer.taxreadout = !InspiredNations.taxTimer.taxreadout;
		return menu;
	}

}
