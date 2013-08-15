package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Map;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class MainHud extends OptionMenu {
	

	public MainHud(PlayerData PDI) {
		super(PDI);
		InspiredGov gov = plugin.global;

		this.options.add(new PromptOption(plugin, PDI, this, "Map", Map.class, OptionUnavail.NOT_UNAVAILABLE));
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
