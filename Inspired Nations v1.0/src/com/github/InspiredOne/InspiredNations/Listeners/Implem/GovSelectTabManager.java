package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class GovSelectTabManager extends ActionManager {

	public String token = "";
	
	public GovSelectTabManager(ActionMenu menu) {
		super(menu);
		this.listeners.add(new GovSelectTabListeners(this));
	}

}
