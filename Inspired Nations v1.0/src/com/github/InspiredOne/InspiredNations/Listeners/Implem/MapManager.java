package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.TabManager;

public class MapManager<T extends ActionMenu> extends TabManager<T> {

	public MapManager(T menu) {
		super(menu);
		listeners.add(new MapListener<MapManager<T>>(this));
		listeners.add(new TabListener<MapManager<T>>(this));
	}
	
	@Override
	public void Update() {
		this.getActionMenu().Update();
		this.preTabEntry = "";
	}
}
