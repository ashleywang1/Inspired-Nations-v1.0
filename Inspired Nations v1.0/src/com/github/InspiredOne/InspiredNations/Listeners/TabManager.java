package com.github.InspiredOne.InspiredNations.Listeners;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;

public class TabManager extends ActionManager {

	public String preTabEntry;
	
	public TabManager(ActionMenu menu) {
		super(menu);
	}
	
	public void setPreTabEntry(String entry) {
		this.preTabEntry = entry;
	}
	
	public String getPreTabEntry() {
		return this.preTabEntry;
	}


	
}
