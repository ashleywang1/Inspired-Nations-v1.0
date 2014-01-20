package com.github.InspiredOne.InspiredNations.Regions.Implem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;

public class Chunkoid extends SelectionMode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821199291297874395L;
	private static final String typeName = "Chunkoid";
	private static final String description = "";


	@Override
	public int getVolume() {
		
		return this.area() * 256;
	}

	public double area() {
		return chunks.size() * 256;
	}

	@Override
	public boolean contains(Location location) {
		return chunks.contains(new Point2DWorld(location.getChunk()));
	}

	@Override
	public boolean intersects(Region region) {

		return false;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addChunk(Point2DWorld position) {
		if(!this.chunks.contains(position)) {
			this.chunks.add(position);
		}
		for(int y = 0; y <= 256; y++) {
			for (int x = position.x*16; x < (position.x + 1)*16; x++) {
				for (int z = position.z*16; z < (position.z + 1)*16; y++) {
					this.blocks.add(new Point3DWorld(x,y,z,position.world));
				}
			}
		}
	}

	@Override
	public HashSet<Point3DWorld> getBlocks() {
		HashSet<Point3DWorld> output = new HashSet<Point3DWorld>();
		for(int y = 0; y <=256; y++) {
			for(Point2DWorld chunk:this.chunks) {
				for(int x = chunk.x*16; x < (chunk.x+1)*16; x++) {
					for(int z = chunk.z*16; z < (chunk.z+1)*16; z++) {
						output.add(new Point3DWorld(x,y,z,chunk.world));
					}
				}
			}
		}
		
		return output;
	}

	@Override
	public HashSet<Point2DWorld> getChunks() {
		HashSet<Point2DWorld> output = new HashSet<Point2DWorld>();
		for(Point2DWorld chunk:this.chunks) {
			output.add(chunk);
		}
		return output;
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

}
