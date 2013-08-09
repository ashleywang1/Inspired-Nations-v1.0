package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Regions.Region;

public class Chunkoid extends Region {

	private static final String typeName = "Chunkoid";
	private static final String description = "";
	
	@Override
	public boolean isIn(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double volume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double area() {
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
	public Class<? extends ActionMenu> getClaimConvo() {
		// TODO Auto-generated method stub
		return null;
	}

}
