package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class MapManager extends ActionManager {

	List<InspiredListener> listener = new ArrayList<InspiredListener>();
	
	public MapManager(InspiredNations instance, ActionMenu menu) {
		super(instance, menu);
		listener.add(new MapListener(plugin, this));
	}

	@Override
	public List<InspiredListener> getPlayerListener() {
		return listener;
	}

}
