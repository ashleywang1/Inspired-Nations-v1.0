package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public class Point3DWorld implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4281467022721428865L;
	public int x;
	public int y;
	public int z;
	public WorldID world;
	
	public Point3DWorld(Location location) {
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
		this.world = new WorldID(location.getWorld());
	}
	public Point3DWorld(int x, int y, int z, WorldID world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(x).
            append(y).
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
        if (!(obj instanceof Point3DWorld))
            return false;

        Point3DWorld rhs = (Point3DWorld) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(x, rhs.x).
            append(y, rhs.y).
            append(z, rhs.z).
            append(world, rhs.world).
            isEquals();
    }
    
    public Location getLocation() {
    
    	return new Location(InspiredNations.plugin.getServer().getWorld(this.world.toString()), x, y, z);
    }
}
