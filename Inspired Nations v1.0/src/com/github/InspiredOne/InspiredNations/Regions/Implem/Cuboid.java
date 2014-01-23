package com.github.InspiredOne.InspiredNations.Regions.Implem;


import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Cuboid extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074456272514021954L;
	private static final String typeName = "Cuboid";
	private static final String description = "";
	private Point3D pointmin;
	private Point3D pointmax;
	
	public void setPoints(Point3D pointone, Point3D pointtwo) {
		pointmin = pointone;
		pointmax = pointtwo;
		if(pointtwo.x < pointmin.x) {
			pointmin.x = pointtwo.x;
			pointmax.x = pointone.x;
		}
		if(pointtwo.y < pointmin.y) {
			pointmin.y = pointtwo.y;
			pointmax.y = pointone.y;
		}
		if(pointtwo.z < pointmin.z) {
			pointmin.z = pointtwo.z;
			pointmax.z = pointone.z;
		}
	}
	
	public Point3D getPointMin() {
		return pointmin;
	}
	
	public Point3D getPointMax() {
		return pointmax;
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
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		return null;
	}

	@Override
	public Cuboid getBoundingCuboid() {
		return this;
	}

	@Override
	public boolean isIn(Region region) {
		return false;
	}

	@Override
	public double volume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Region region) {
		Cuboid other = region.getBoundingCuboid();
		if(this.getWorld().equals(region.getWorld())) {
			if(this.pointmax.y > other.pointmin.y && this.pointmin.y < other.pointmax.y) {
				if(this.pointmax.x > other.pointmin.x && this.pointmin.x < other.pointmax.x) {
					if(this.pointmax.z > other.pointmin.z && this.pointmin.z < other.pointmax.z) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean contains(Point3D location) {
		// TODO Auto-generated method stub
		return false;
	}


}
