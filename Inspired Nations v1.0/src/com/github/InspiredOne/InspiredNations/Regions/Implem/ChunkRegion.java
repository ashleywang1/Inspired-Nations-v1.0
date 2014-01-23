package com.github.InspiredOne.InspiredNations.Regions.Implem;

import com.github.InspiredOne.InspiredNations.Exceptions.IncorrectUnitOfTheCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ChunkRegion extends NonCummulativeRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1407842833341186604L;
	
	Point2D coordinate;
	
	public Chunk(Chunk chunk) {
		
	}

	@Override
	public Cuboid getBoundingCuboid() {
		
		return null;
	}

	@Override
	protected boolean intersects(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean IsIn(CummulativeRegion region) throws IncorrectUnitOfTheCummulativeRegion {
		if(region instanceof Chunkoid) {
			return region.getRegions().contains(this);
		}
		else {
			throw new IncorrectUnitOfTheCummulativeRegion();
		}
	}

	@Override
	public boolean Intersects(CummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIn(NonCummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

}
