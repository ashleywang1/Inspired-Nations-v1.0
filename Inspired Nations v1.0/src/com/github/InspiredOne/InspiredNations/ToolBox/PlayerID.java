package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.entity.Player;

import com.github.InspiredOne.InspiredNations.Economy.Currency;

public class PlayerID implements Serializable {

	private static final long serialVersionUID = 4523105693338266817L;
	private String name;
	
	public PlayerID(Player player) {
		this.name = player.getName();
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(name).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Currency))
            return false;

        PlayerID rhs = (PlayerID) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(name, rhs.getName()).
            isEquals();
    }
    
    @Override
    public String toString() {
    	return name;
    }
	
}
