package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimPolygonPrism;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class ClaimPolygonPrismManager<T extends ClaimPolygonPrism> extends ActionManager<T> {

	public ClaimPolygonPrismManager(T menu) {
		super(menu);
		this.listeners.add(new ClaimPolygonPrismListener<ClaimPolygonPrismManager<?>>(this));
	}

}
