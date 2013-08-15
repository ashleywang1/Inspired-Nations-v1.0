package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class MainHud extends OptionMenu {
	

	public MainHud(InspiredNations plugin, PlayerData PDI) {
		super(plugin, PDI);
		InspiredGov gov = this.plugin.global;

		this.options.add(new PromptOption(this, "Map", new Map(plugin, PDI), OptionUnavail.NOT_UNAVAILABLE));
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Welcome to the HUD! Enter an option number.";
	}

	@Override
	public Menu getPreviousMenu() {
		return this.getSelf();
	}

}
