package com.github.InspiredOne.InspiredNations.Regions;

import java.io.Serializable;
import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.SelectionNotMadeException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;

public abstract class SelectionMode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1630115464882329018L;

	/**
	 * Returns the type name to be used in menus
	 * @return
	 */
	public abstract String getTypeName();
	/**
	 * Returns the description to be used in menus
	 * @return 
	 */
	public abstract String getDescription();
	/**
	 * Returns the first of the chain of menus used to claim it.
	 * @param PDI	The player that is claiming land.
	 * @param previous	the menu to go to after everything is done.
	 * @return
	 */
	public abstract Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov);
	
	/**
	 * Gets all the blocks that the selection currently holds
	 * @return
	 */
	public abstract HashSet<Integer> getBlocks() throws SelectionNotMadeException;
	
	public int getVolume() {
		try {
			return this.getBlocks().size();
		} catch (SelectionNotMadeException e) {
			return 0;
		}
	}
	
}
