package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class MenuUpdateManager<T extends ActionMenu> extends ActionManager<T> {

	public MenuUpdateManager(T menu) {
		super(menu);
		this.listeners.add(new MenuUpdateListener<ActionManager<?>>(this));
	}
}
