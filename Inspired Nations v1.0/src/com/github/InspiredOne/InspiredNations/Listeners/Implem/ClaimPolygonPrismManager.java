package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Exceptions.PointsInDifferentWorldException;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand.ClaimPolygonPrism;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.PolygonPrism;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ClaimPolygonPrismManager<T extends ClaimPolygonPrism> extends ActionManager<T> {

	public PolygonPrism prism = new PolygonPrism();
	private ClaimPolygonPrismListener<ClaimPolygonPrismManager<?>> listener = new ClaimPolygonPrismListener<ClaimPolygonPrismManager<?>>(this);
	
	public ClaimPolygonPrismManager(T menu) {
		super(menu);
		this.listeners.add(listener);
	}
	
	@Override
	public void Update() {
		try {
			prism.addVertex(listener.position);
		} catch (PointsInDifferentWorldException e) {
			this.getActionMenu().setError(MenuError.POINTS_IN_DIFFERENT_WORLDS());
			e.printStackTrace();
		}
		this.getActionMenu().setError(MenuError.NO_ERROR());
		this.getActionMenu().Update();
	}

}
