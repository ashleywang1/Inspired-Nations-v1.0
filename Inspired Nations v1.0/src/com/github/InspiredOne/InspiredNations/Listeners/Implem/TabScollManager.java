package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class TabScollManager extends ActionManager {

	List<InspiredListener> listeners = new ArrayList<InspiredListener>();
	
	public TabScollManager(ActionMenu menu) {
		super(menu);
	}

	@Override
	public List<InspiredListener> getPlayerListener() {
		listeners.add(new TabListener(this));
		return listeners;
	}

	@Override
	public void Update() {
		this.getActionMenu().Update();
	}
}
