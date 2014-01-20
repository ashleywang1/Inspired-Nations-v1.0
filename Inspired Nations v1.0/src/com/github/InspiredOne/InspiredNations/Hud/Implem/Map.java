package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class Map extends ActionMenu {

	public Map(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getFiller() {
		return Tools.drawMap(PDI, 16, Country.class);
	}

	@Override
	public String getHeader() {
		return "Map";
	}

	@Override
	public Menu PreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public Menu NextMenu(String input) {
		this.setError(MenuError.NOT_AN_OPTION());
		return this.getSelf();
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		managers.add(new MapManager<Map>(this));
	}

	@Override
	public void actionResponse() {
	}
}
