package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.TabManager;

public class TabScrollManager<T extends ActionMenu> extends TabManager<T> {
	
	public boolean updateFromTabScroll = false;
	
	public TabScrollManager(T menu) {
		super(menu);
		listeners.add(new TabListener<TabScrollManager<T>>(this));
	}

	@Override
	public void Update() {
		this.updateFromTabScroll = true;
		if(this.preTabEntry.equals("+")) {
		}
		else if(this.preTabEntry.equals("-")) {
		}
		else if(!this.preTabEntry.isEmpty()){
		}
		else {
		}
		this.getActionMenu().Update();
		this.updateFromTabScroll = false;
	}
}
