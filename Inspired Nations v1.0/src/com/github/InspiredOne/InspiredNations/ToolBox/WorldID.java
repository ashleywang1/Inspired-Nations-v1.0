package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.World;

import com.github.InspiredOne.InspiredNations.InspiredNations;

/**
 * This is a class to encapsulate worlds so that I have an easy method of comparing worlds without
 * having to fuss about with strings and world names.
 * @author Jedidiah Phillips
 *
 */
public class WorldID implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2025799081947713318L;
	private String worldname;
	
	public WorldID(World world) {
		worldname = world.getName();
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(worldname).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof WorldID))
            return false;

        WorldID rhs = (WorldID) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(worldname, rhs.worldname).
            isEquals();
    }
    
    @Override
    public String toString() {
    	return this.worldname;
    }

    public World getWorld() {
    	return InspiredNations.plugin.getServer().getWorld(this.worldname);
    }
}
