package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class Map extends ActionMenu {

	private int Zoom = 4;
	private MapManager<Map> manager;
	
	public Map(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getFiller() {
		return Tools.drawMap(PDI, (int) Math.pow(2, Zoom), Country.class);
	}

	@Override
	public String getHeader() {
		return "Map: Scale 1:" + (int) Math.pow(2, Zoom);
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
		if(this.manager.preTabEntry.equalsIgnoreCase("+") && Zoom > 0) {
			Zoom--;
		}
		else if(this.manager.preTabEntry.equalsIgnoreCase("-") && Zoom < 8) {
			Zoom++;
		}
	}
}
