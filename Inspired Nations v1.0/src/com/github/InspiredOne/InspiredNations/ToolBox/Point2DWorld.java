package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

public class Point2DWorld implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2001604739255193950L;
	public int x;
	public int z;
	public WorldID world;
	
	public Point2DWorld(int x, int z, World world) {
		this.x = x;
		this.z = z;
		this.world = new WorldID(world);
	}
	
	public Point2DWorld(Chunk chunk) {
		this.x = chunk.getX();
		this.z = chunk.getZ();
		this.world = new WorldID(chunk.getWorld());
	}
	
	public Point2DWorld(Location location) {
		this.x = location.getBlockX();
		this.z = location.getBlockZ();
		this.world = new WorldID(location.getWorld());
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(x).
            append(z).
            append(world).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Point2DWorld))
            return false;

        Point2DWorld rhs = (Point2DWorld) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(x, rhs.x).
            append(z, rhs.z).
            append(world, rhs.world).
            isEquals();
    }
	
}
