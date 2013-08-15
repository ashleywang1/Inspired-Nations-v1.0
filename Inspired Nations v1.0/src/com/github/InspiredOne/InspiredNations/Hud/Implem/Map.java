package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class Map extends ActionMenu {

	List<ActionManager> managers= new ArrayList<ActionManager>();
	
	public Map(PlayerData PDI) {
		super(PDI);
		managers.add(new MapManager(plugin, this));

	}

	@Override
	public List<ActionManager> getActionManager() {
		return managers;
	}

	@Override
	public String getFiller() {
		return Tools.drawMap(plugin, PDI, 16, Country.class);
	}

	@Override
	public String getHeader() {
		return "Map";
	}

	@Override
	public Menu PreviousMenu() {
		return MenuTools.getMenuInstance(plugin, PDI, MainHud.class);
	}

	@Override
	public Menu NextMenu(String input) {
		this.setError(MenuError.NOT_AN_OPTION);
		return this.getSelf();
	}

}
