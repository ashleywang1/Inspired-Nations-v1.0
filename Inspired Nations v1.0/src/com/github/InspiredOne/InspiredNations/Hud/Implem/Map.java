package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class Map extends ActionMenu {

	private MapManager<Map> manager;
	
	public Map(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getFiller() {
		return manager.drawMap(Country.class);
	}

	@Override
	public String getHeader() {
		return "Map 1:" + (int) Math.pow(2, manager.zoom);
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public Menu getNextMenu(String input) {
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
		manager = new MapManager<Map>(this);
		managers.add(manager);
	}

	@Override
	public void actionResponse() {
	}
}
