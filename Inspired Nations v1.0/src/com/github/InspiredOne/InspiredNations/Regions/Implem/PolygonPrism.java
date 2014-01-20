package com.github.InspiredOne.InspiredNations.Regions.Implem;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.WorldID;

public class PolygonPrism extends SelectionMode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6427556511666509346L;
	private static final String typeName = "Polygon Prism";
	private static final String description = "";
	private HashSet<Point3D> blocks;
	private int ymin;
	private int ymax;
	private WorldID world;
	private Polygon polygon = new Polygon();
	
	public PolygonPrism(Point[] points, World world) {
		polygon.reset();
		ymin = 0;
		ymax = 256;
		this.world = new WorldID(world);
		for (int i = 0; i < points.length; i++) {
			polygon.addPoint(points[i].x, points[i].y);
		}
	}
	
	public PolygonPrism(Location[] points) {
		int[] x = new int[points.length];
		int[] z = new int[points.length];
		polygon.reset();
		world = new WorldID(points[0].getWorld());
		ymin = points[0].getBlockY();
		ymax = ymin;
		for (int i = 0; i < points.length; i++) {
			if (points[i].getBlockY() < ymin) {
				ymin = points[i].getBlockY();
			}
			if (points[i].getBlockY() > ymax) {
				ymax = points[i].getBlockY();
			}
			x[i] = points[i].getBlockX();
			z[i] = points[i].getBlockZ();
		}
		polygon = new Polygon(x, z, points.length);
	}

	@Override
	public double volume() {
		
		return this.area()*(ymax-ymin + 1);
	}

	@Override
	public double area() {
    	double sum = 0.0;
    	for (int i = 0; i < polygon.npoints; i++) {
    		if (!((i + 1) < polygon.npoints)) {
    			sum = sum + ((polygon.xpoints[i] * polygon.ypoints[0]) - (polygon.ypoints[i] * polygon.xpoints[0]));
    		}
    		else {
    			sum = sum + ((polygon.xpoints[i] * polygon.ypoints[i + 1]) - (polygon.ypoints[i] * polygon.xpoints[i + 1]));
    		}
    	}
    	return (int) Math.abs(.5 * sum);
	}

	@Override
	public boolean contains(Location location) {
		location.getBlockX();
		if (this.world.equals(new WorldID(location.getWorld()))) {
			if (polygon.contains(location.getBlockX(), location.getBlockZ())) {
				if (location.getBlockY() <= ymax && location.getBlockY() >= ymin) {
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
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
	public HashSet<Point3D> getBlocks() {
		
		
		return null;
	}
	
	// A method to determine if a polygon is simple
	public boolean isSimple() {
		Polygon poly = this.polygon;
		Vector<Line2D> lines = new Vector<Line2D>();
		Line2D line;
		Line2D line2;
		for (int i = 0; i < poly.npoints; i++) {
			if (!((i+1)<poly.npoints)) {
				line = new Line2D.Double(new Point(poly.xpoints[i], poly.ypoints[i]), new Point(poly.xpoints[0], poly.ypoints[0]));
			}
			else {
				line = new Line2D.Double(new Point(poly.xpoints[i], poly.ypoints[i]), new Point(poly.xpoints[i+1], poly.ypoints[i+1]));
			}
			lines.add(line);
		}
		for (int i = 0; i < lines.size(); i++) {
			line = (Line2D) lines.get(i).clone();
			for (int j = 0; j < lines.size(); j++) {
				line2 = (Line2D) lines.get(j).clone();
				if (i != j && i != (j+1) && i != (j-1) && !(i == 0 && j == lines.size() - 1) && !(i == lines.size() - 1 && j == 0) && line.intersectsLine(line2)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

}
