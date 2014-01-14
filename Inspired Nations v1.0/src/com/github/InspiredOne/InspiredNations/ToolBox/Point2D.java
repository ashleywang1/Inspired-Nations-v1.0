package com.github.InspiredOne.InspiredNations.ToolBox;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class Point2D {

	public int x;
	public int z;
	
	public Point2D(int x, int z) {
		this.x = x;
		this.z = z;
	}
	
	public Point2D(Chunk chunk) {
		this.x = chunk.getX();
		this.z = chunk.getZ();
	}
	
	public Point2D(Location location) {
		this.x = location.getBlockX();
		this.z = location.getBlockZ();
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(x).
            append(z).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Point2D))
            return false;

        Point2D rhs = (Point2D) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(x, rhs.x).
            append(z, rhs.z).
            isEquals();
    }
	
}
