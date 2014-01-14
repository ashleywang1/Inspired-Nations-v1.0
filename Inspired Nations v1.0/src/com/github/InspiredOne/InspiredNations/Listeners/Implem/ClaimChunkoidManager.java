package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoidManager extends ActionManager {

	Point2D position;
	
	public ClaimChunkoidManager(ActionMenu menu) {
		super(menu);
		this.listeners.add(new ClaimChunkoidListener(this));
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}


}
