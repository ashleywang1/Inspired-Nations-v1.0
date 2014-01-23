package com.github.InspiredOne.InspiredNations.Regions.Implem;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.IncorrectUnitOfTheCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.WorldID;

public class Cuboid extends NonCummulativeRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6074456272514021954L;
	private static final String typeName = "Cuboid";
	private static final String description = "";
	private Point3D pointmin;
	private Point3D pointmax;
	
	public Cuboid() {
		
	}
	
	public Cuboid(Point3D one, Point3D two) {
		setPoints(one,two);
	}
	
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
	public int volume() {
		int length = this.pointmax.x - this.pointmin.x + 1;
		int height = this.pointmax.y - this.pointmin.y + 1;
		int width = this.pointmax.z - this.pointmin.z + 1;
		return length*height*width;
	}

	@Override
	public boolean contains(Point3D location) {
		int x = location.x;
		int y = location.y;
		int z = location.z;
		
		if (x >= this.pointmin.x && x <= this.pointmax.x && location.world.equals(this.getWorld())) {
			if (y >= this.pointmin.y && y <= this.pointmax.y) {
				if (z >= this.pointmin.z && z <= this.pointmax.z) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean Intersects(CummulativeRegion region) {
		for(NonCummulativeRegion test:region.getRegions()) {
			if(this.Intersects(test)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean intersects(NonCummulativeRegion region) {
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
	public boolean IsIn(CummulativeRegion region)
			throws IncorrectUnitOfTheCummulativeRegion {
		Point3D point;
		for(int x = this.pointmin.x; x <= this.pointmax.x; x++) {
			for(int y = this.pointmin.y; y <= this.pointmax.y; y++) {
				for(int z = this.pointmin.z; z <= this.pointmax.z; z++) {
					point = new Point3D(x, y, z, this.pointmax.world);
					if(!region.contains(point)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean isIn(NonCummulativeRegion region) {
		Point3D point;
		for(int x = this.pointmin.x; x <= this.pointmax.x; x++) {
			for(int y = this.pointmin.y; y <= this.pointmax.y; y++) {
				for(int z = this.pointmin.z; z <= this.pointmax.z; z++) {
					point = new Point3D(x, y, z, this.pointmax.world);
					if(!region.contains(point)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public WorldID getWorld() {
		return this.pointmax.world;
	}


}
