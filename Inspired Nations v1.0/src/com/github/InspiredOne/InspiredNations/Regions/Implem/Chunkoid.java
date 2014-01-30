package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Chunk;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class Chunkoid extends CummulativeRegion<ChunkRegion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821199291297874395L;
	private static final String typeName = "Chunkoid";
	private static final String description = "A region made up by chunks going from bedrock to world height.";
	
	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		return new ClaimChunkoid(PDI, previous, gov);
	}
	
	@Override
	public boolean IsIn(Region region) {
		if(region instanceof NonCummulativeRegion){
			return this.IsIn((NonCummulativeRegion) region);
		}
		else {
			return this.IsIn((CummulativeRegion<?>) region);
		}
	}
	
	public void addChunk(Chunk chunk) {
		Point2D position = new Point2D(chunk);
		addChunk(position);
	}
	
	public void addChunk(Point2D position) {
		ChunkRegion chunk = new ChunkRegion(position);
		addChunk(chunk);
	}
	
	public void addChunk(ChunkRegion region) {
		this.getRegions().add(region);	
	}
	
	public void removeChunk(Chunk chunk) {
		Point2D position = new Point2D(chunk);
		removeChunk(position);
	}
	public void removeChunk(Point2D position) {
		ChunkRegion chunk = new ChunkRegion(position);
		removeChunk(chunk);
	}
	public void removeChunk(ChunkRegion chunk) {
		this.getRegions().remove(chunk);
	}

	@Override
	protected boolean instantiated() {
		return true;
	}

	@Override
	public Menu getUnclaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean IsIn(CummulativeRegion<?> region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Intersects(CummulativeRegion<?> region) {
		// TODO Auto-generated method stub
		return false;
	}
}
