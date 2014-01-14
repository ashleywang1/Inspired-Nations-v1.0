package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
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
	private Point3D point1;
	private Point3D point2;
	
	@Override
	public boolean isIn(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double volume() {
		if(point1 == null || point2 == null) {
			return 0;
		}
		return 0;
	}

	@Override
	public double area() {
		if(point1 == null || point2 == null) {
			return 0;
		}
		return 0;
	}

	@Override
	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		return false;
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
