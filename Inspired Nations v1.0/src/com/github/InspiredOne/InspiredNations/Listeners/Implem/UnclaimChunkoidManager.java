package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand.UnclaimChunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class UnclaimChunkoidManager<T extends UnclaimChunkoid> extends ChunkoidManager<T> {

	public ChunkoidListener<UnclaimChunkoidManager<T>> listener;
	public boolean unclaim = false;
	
	public UnclaimChunkoidManager(T menu, Point2D initialChunk) {
		super(menu, initialChunk);
		listener = new ChunkoidListener<UnclaimChunkoidManager<T>>(this);
	}

	@Override
	public void setPosition(Point2D chunk) {
		
	}

	public void setUnclaim(boolean unclaim) {
		this.unclaim = unclaim;
		this.setPosition(this.getPosition());
	}
}
