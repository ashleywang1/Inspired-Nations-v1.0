package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public abstract class ChunkoidManager<T extends ActionMenu> extends ActionManager<T> {
	protected Point2D position;
	
	public ChunkoidManager(T menu, Point2D initialChunk) {
		super(menu);
		this.listeners.add(new ChunkoidListener<ChunkoidManager<T>>(this));
		this.position = initialChunk;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public abstract void setPosition(Point2D chunk);
}
