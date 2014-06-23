package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;

public class ToggleTimerOption extends Option {
	
	PlayerData PDI;

	public ToggleTimerOption(OptionMenu menu, String label, String description) {
		super(menu, label, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu response(String input) {
		InspiredNations.taxTimer.taxreadout = !InspiredNations.taxTimer.taxreadout;
		if (PDI.getTimerState()=="On") {
			PDI.setTimerState("Off");
		} else {
			PDI.setTimerState("On");
		}
		return menu;
	}

}
