package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov.PickSelfType;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class MainHud extends OptionMenu {
	

	public MainHud(PlayerData PDI) {
		super(PDI);
		InspiredGov gov = this.plugin.global;

		this.options.add(new PromptOption(this, "Map", new Map(PDI), OptionUnavail.NOT_UNAVAILABLE));
		this.options.add(new PromptOption(this, "New Country", new PickSelfType(PDI, Country.class), OptionUnavail.NOT_UNAVAILABLE));
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

	@Override
	public boolean passBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

}
