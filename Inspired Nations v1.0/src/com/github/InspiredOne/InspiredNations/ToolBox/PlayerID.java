package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
/**
 * My own implementation to make changes in the player identification simple to deal
 * with. As of Minecraft 1.7.4 player names are unique and non-spoofable, however the 
 * developers hint that this may not always be the case, so this class should be used
 * when trying to make sure a Player is who he/she says they are. 
 * @author Jedidiah Phillips
 *
 */
public class PlayerID implements Serializable, Nameable {

	private static final long serialVersionUID = 4523105693338266817L;
	private String name;
	
	public PlayerID(Player player) {
		this.name = player.getName();
	}
	public PlayerID(OfflinePlayer player) {
		this.name = player.getName();
	}
	public PlayerID(CommandSender player) {
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
        if (!(obj instanceof PlayerID))
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

	public PlayerData getPDI() {
		return InspiredNations.playerdata.get(this);
	}

	@Override
	public void setName(String name) {
		
	}
	
	@Override
	public String getDisplayName(PlayerData PDI) {
		return this.getPDI().getDisplayName(PDI);
	}
	
}
