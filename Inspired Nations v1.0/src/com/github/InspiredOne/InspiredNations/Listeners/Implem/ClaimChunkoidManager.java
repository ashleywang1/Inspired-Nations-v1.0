package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Chunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoidManager<T extends ClaimChunkoid> extends ActionManager<T> {

	private Point2D position;
	private boolean claiming = false;
	private Chunkoid region;
	
	public ClaimChunkoidManager(T menu, Point2D initialChunk, Chunkoid region) {
		super(menu);
		this.region = region;
		this.listeners.add(new ClaimChunkoidListener<ClaimChunkoidManager<T>>(this));
		this.position = initialChunk;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setClaiming(boolean claiming) {
		this.claiming = claiming;
		setPosition(position);
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
		if(claiming) {
			// Check if allowed to
			
			region.addChunk(position);
		}

	}


}
