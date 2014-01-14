package com.github.InspiredOne.InspiredNations.Regions.Implem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class Chunkoid extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821199291297874395L;
	private static final String typeName = "Chunkoid";
	private static final String description = "";
	private List<Point2D> chunks = new ArrayList<Point2D>();
	
	@Override
	public boolean isIn(Region region) {
		
		return false;
	}

	@Override
	public double volume() {
		
		return this.area() * 256;
	}

	@Override
	public double area() {
		return chunks.size() * 256;
	}

	@Override
	public boolean contains(Location location) {
		return chunks.contains(new Point2D(location.getChunk()));
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
	
	public void addChunk(Point2D position) {
		if(!this.chunks.contains(position)) {
			this.chunks.add(position);
		}
	}

}
