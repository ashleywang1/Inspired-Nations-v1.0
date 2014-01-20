package com.github.InspiredOne.InspiredNations.Regions;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -330203131653502896L;
	
	HashSet<Point3D> blocks = new HashSet<Point3D>();
	
	public Region(SelectionMode selection) {
		
	}
	
	public void addBlocks(SelectionMode select) {
		this.getBlocks().addAll(select.getBlocks());
	}
	
	public void removeBlocks(SelectionMode select) {
		this.getBlocks().removeAll(select.getBlocks());
	}
	
	
	/**
	 * Gets a set of all the blocks in the volume of the region
	 * @return
	 */
	public HashSet<Point3D> getBlocks() {
		return blocks;
	}

	/**
	 * Returns true if the entire region is within the input region
	 * @param region
	 * @return	
	 */
	public boolean isIn(Region region) {
		return region.getBlocks().containsAll(this.getBlocks());
	}
	/**
	 * Returns the volume in cubic meters
	 * @return
	 */
	public double volume() {
		return this.getBlocks().size();
	}
	/**
	 * Returns true if the location is within the region
	 * @param location	the location to test
	 * @return
	 */
	public boolean contains(Location location) {
		return this.getBlocks().contains(new Point3D(location));
	}
	/**
	 * Returns true if the regions overlap
	 * @param region
	 * @return
	 */
	public boolean intersects(Region region) {
		return !Collections.disjoint(this.getBlocks(), region.getBlocks());
	}

}
