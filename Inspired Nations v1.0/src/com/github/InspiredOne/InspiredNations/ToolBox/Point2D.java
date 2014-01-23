package com.github.InspiredOne.InspiredNations.ToolBox;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

public class Point2D extends Point3D {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2941615375716870644L;

	public Point2D(int x, int z, World world) {
		super(x, 0, z, world);
	}
	
	public Point2D(int x, int z, WorldID world) {
		super(x, 0, z, world);
	}
	
	public Point2D(Location location) {
		super(location);
		this.y = 0;
	}

	public Point2D(Chunk chunk) {
		this(chunk.getX(), chunk.getZ(), chunk.getWorld());
	}

}
