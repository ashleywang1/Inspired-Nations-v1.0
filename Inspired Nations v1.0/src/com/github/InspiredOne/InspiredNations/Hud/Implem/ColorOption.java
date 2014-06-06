package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Hud.DarkTheme;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Theme;

public class ColorOption extends Option {
	
	public Theme normal;

	public ColorOption(OptionMenu menu, String label, Theme theme) {
		super(menu, label);
		normal = theme;
	}

	@Override
	public Menu response(String input) {
		// get the player data
		menu.PDI.theme = normal;
		return this.menu;
	}

}
