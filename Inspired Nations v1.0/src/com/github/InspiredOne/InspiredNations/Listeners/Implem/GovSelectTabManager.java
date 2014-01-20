package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class GovSelectTabManager<T extends ActionMenu> extends ActionManager<T> {

	public String token = "";
	
	public GovSelectTabManager(T menu) {
		super(menu);
		this.listeners.add(new GovSelectTabListeners<GovSelectTabManager<T>>(this));
	}

}
