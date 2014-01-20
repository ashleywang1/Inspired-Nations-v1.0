package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class MapManager<T extends ActionMenu> extends ActionManager<T> {

	public MapManager(T menu) {
		super(menu);
		listeners.add(new MapListener<MapManager<?>>(this));
	}
}
