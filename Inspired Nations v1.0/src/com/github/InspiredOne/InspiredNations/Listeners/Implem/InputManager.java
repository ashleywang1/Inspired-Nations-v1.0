package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import java.util.Collection;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class InputManager<T extends ActionMenu> extends ActionManager<T> {

	private Collection<String> tabOptions;
	
	public InputManager(T menu, Collection<String> tabOptions) {
		super(menu);
		this.setTabOptions(tabOptions);
		this.listeners.add(new InputListener<InputManager<T>>(this));
	}

	public Collection<String> getTabOptions() {
		return tabOptions;
	}

	public void setTabOptions(Collection<String> tabOptions) {
		this.tabOptions = tabOptions;
	}

}
