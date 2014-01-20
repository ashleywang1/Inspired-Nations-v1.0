package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Chunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoidManager<T extends ClaimChunkoid> extends ActionManager<T> {

	private Point2D position;
	public Chunkoid selection = new Chunkoid();
	private boolean claiming = false;
	
	public ClaimChunkoidManager(T menu, Point2D initialChunk) {
		super(menu);
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
			this.selection.addChunk(position);
			this.getActionMenu().region.addBlocks(selection);
			this.selection = new Chunkoid();
		}

	}


}
