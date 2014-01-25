package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Chunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoidManager<T extends ClaimChunkoid> extends ActionManager<T> {

	private Point2D position;
	private boolean claiming = false;
	private Chunkoid region = new Chunkoid();
	
	public ClaimChunkoidManager(T menu, Point2D initialChunk) {
		super(menu);
		this.listeners.add(new ClaimChunkoidListener<ClaimChunkoidManager<T>>(this));
		this.position = initialChunk;
		region = (Chunkoid) menu.region.clone();
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

			try {
				region.addChunk(position);
				this.getActionMenu().gov.setLand((Chunkoid) region.clone());
			} catch (BalanceOutOfBoundsException e) {
				region.removeChunk(position);
				this.getActionMenu().setError(MenuError.NOT_ENOUGH_MONEY());
			} catch (InspiredGovTooStrongException e) {
				region.removeChunk(position);
				this.getActionMenu().setError(MenuError.GOV_TOO_STRONG(e.gov));
			} catch (RegionOutOfEncapsulationBoundsException e) {
				region.removeChunk(position);
				this.getActionMenu().setError(MenuError.CLAIM_OUT_OF_BOUNDS(e.gov));
			} catch (InsufficientRefundAccountBalanceException e) {
				region.removeChunk(position);
				// TODO Figure out what to do here.
			};
		}
	}
}
