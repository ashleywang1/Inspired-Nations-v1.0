package com.github.InspiredOne.InspiredNations.ToolBox;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;

public class Point3D {

	public int x;
	public int y;
	public int z;
	
	public Point3D(Location location) {
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(x).
            append(y).
            append(z).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Point3D))
            return false;

        Point3D rhs = (Point3D) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(x, rhs.x).
            append(y, rhs.y).
            append(z, rhs.z).
            isEquals();
    }
}
