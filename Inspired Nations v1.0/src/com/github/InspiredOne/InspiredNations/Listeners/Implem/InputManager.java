package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import java.util.Collection;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class InputManager extends ActionManager {

	private Collection<String> tabOptions;
	
	public InputManager(ActionMenu menu, Collection<String> tabOptions) {
		super(menu);
		this.setTabOptions(tabOptions);
		this.listeners.add(new InputListener(this));
	}

	public Collection<String> getTabOptions() {
		return tabOptions;
	}

	public void setTabOptions(Collection<String> tabOptions) {
		this.tabOptions = tabOptions;
	}

}
