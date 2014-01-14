package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.WorldID;

public class Cuboid extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074456272514021954L;
	private static final String typeName = "Cuboid";
	private static final String description = "";
	private Point3D pointmin;
	private Point3D pointmax;
	private WorldID world;
	
	@Override
	public boolean isIn(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

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
		if(!this.world.equals(new WorldID(location.getWorld()))) {
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
	public boolean intersects(Region region) {
		// TODO Auto-generated method stub
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

}
