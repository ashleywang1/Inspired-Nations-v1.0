package com.github.InspiredOne.InspiredNations.ToolBox;
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
	
	public void setName(String name);
}
