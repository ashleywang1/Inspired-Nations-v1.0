package com.github.InspiredOne.InspiredNations.Regions.Implem;


import java.util.HashSet;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.WorldID;

public class Cuboid extends SelectionMode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074456272514021954L;
	private static final String typeName = "Cuboid";
	private static final String description = "";
	private Point3D pointmin;
	private Point3D pointmax;
	private HashSet<Point3D> blocks;
	
	@Override
	public double volume() {
		if(pointmin == null || pointmax == null) {
			return 0;
		}
		int height = (pointmax.y - pointmin.y) + 1;
		return height*area();
	}

	@Override
	public double area() {
		if(pointmin == null || pointmax == null) {
			return 0;
		}
		
		int width = (pointmax.x - pointmin.x) + 1;
		int length = (pointmax.z - pointmin.z) + 1;
		return width*length;
	}

	@Override
	public boolean contains(Location location) {
		if(!this.pointmin.world.equals(new WorldID(location.getWorld()))) {
			return false;
		}
		if(location.getBlockY() >= this.pointmin.y && location.getBlockY() <= this.pointmax.y) {
			return false;
		}
		if(location.getBlockX() >= this.pointmin.x && location.getBlockX() <= this.pointmax.x) {
			return false;
		}
		if(location.getBlockZ() >= this.pointmin.z && location.getBlockZ() <= this.pointmax.z) {
			return false;
		}
		return true;
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

	@Override
	public HashSet<Point3DWorld> getBlocks() {
		if(blocks != null) {
			return blocks;
		}
		else {
			HashSet<Point3DWorld> output = new HashSet<Point3DWorld>();
			for(int x = pointmin.x; x <= pointmax.x; x++) {
				for(int y = pointmin.y; y <= pointmax.y; y++) {
					for(int z = pointmin.z; z <= pointmax.z; z++) {
						output.add(new Point3DWorld(x,y,z,pointmin.world));
					}
				}
			}
			return output;
		}
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}
}
