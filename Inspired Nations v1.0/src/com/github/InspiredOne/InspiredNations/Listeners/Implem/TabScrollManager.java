package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class TabScrollManager extends ActionManager {

	public TabScrollManager(ActionMenu menu) {
		super(menu);
		listeners.add(new TabListener(this));
	}

	@Override
	public void Update() {
		this.getActionMenu().Update();
	}
}
