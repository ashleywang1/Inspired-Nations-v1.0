package com.github.InspiredOne.InspiredNations.Regions.Implem;

import java.util.HashSet;

import org.bukkit.Chunk;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Chunkoid extends CummulativeRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821199291297874395L;
	private static final String typeName = "Chunkoid";
	private static final String description = "";
	private HashSet<Integer> blocks = new HashSet<Integer>();

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
	
	public void addChunk(Chunk chunk) {
		Point2D position = new Point2D(chunk.getX(), chunk.getZ(), chunk.getWorld());
		addChunk(position);

	}
	
	public void addChunk(Point2D position) {
		for(int y = 0; y <= 256; y++) {
			for (int x = position.x*16; x < (position.x + 1)*16; x++) {
				for (int z = position.z*16; z < (position.z + 1)*16; z++) {
					this.blocks.add((new Point3D(x,y,z,position.world)).hashCode());
				}
			}
		}
	}

	@Override
	public boolean contains(Point3D location) {
		Location spot = new Location(location.world.getWorld(), location.y, location.z, location.x);
		return true;
	}
}
