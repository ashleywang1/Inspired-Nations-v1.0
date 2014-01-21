package com.github.InspiredOne.InspiredNations.Regions.Implem;


import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.SelectionNotMadeException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Cuboid extends SelectionMode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074456272514021954L;
	private static final String typeName = "Cuboid";
	private static final String description = "";
	private Point3D pointmin;
	private Point3D pointmax;
	private HashSet<Integer> blocks = new HashSet<Integer>();
	
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
		blocks.clear();
		for(int x = pointmin.x; x <= pointmax.x; x++) {
			for(int y = pointmin.y; y <= pointmax.y; y++) {
				for(int z = pointmin.z; z <= pointmax.z; z++) {
					blocks.add((new Point3D(x,y,z,pointmin.world)).hashCode());
				}
			}
		}
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
	public HashSet<Integer> getBlocks() throws SelectionNotMadeException {
		return blocks;
	}


}
