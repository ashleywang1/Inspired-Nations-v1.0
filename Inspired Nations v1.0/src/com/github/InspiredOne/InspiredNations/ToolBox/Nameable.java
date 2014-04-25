package com.github.InspiredOne.InspiredNations.ToolBox;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;

/**
 * Used by menus to list a bunch of named objects
 * @author Jedidiah E. Phillips
 *
 */
public interface Nameable {
	/**
	 * Used to get the name of the object
	 * @return	the name of the object
	 */
	public String getName();
	
	public void setName(String name) throws NameAlreadyTakenException;
	/**
	 * Gets a string that includes the name an more information inline
	 * @return
	 */
	public String getDisplayName(PlayerData viewer);
}
