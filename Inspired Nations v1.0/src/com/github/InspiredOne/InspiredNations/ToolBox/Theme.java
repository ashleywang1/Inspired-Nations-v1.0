package com.github.InspiredOne.InspiredNations.ToolBox;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;

public abstract class Theme implements Nameable {

	
	public Theme() {
		
	}

	@Override
	public void setName(String name) throws NameAlreadyTakenException {

	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return this.getName();
	}
	
	/**
	 * Header
	 */
	public abstract String HEADER();
	/**
	 * Subheader
	 */
	public abstract String SUBHEADER();
	/**
	 * Used to say what the proceeding value is.
	 */
	public abstract String LABEL();
	/**
	 * Anything that is calculated information displayed for the viewer 
	 */
	public abstract String VALUE();
	/**
	 * Additional text describing the value
	 */
	public abstract String VALUEDESCRI();
	/**
	 * Used for the divider between sections of the HUD
	 */
	public abstract String DIVIDER();
	
	/**
	 * The color of the options
	 */
	public abstract String OPTION();
	/**
	 * The color of the number correlating to the option
	 */
	public abstract String OPTIONNUMBER();
	/**
	 * Used for text that describes an option
	 */
	public abstract String OPTIONDESCRIP();
	/**
	 * Color to be used for an unavailable option
	 */
	public abstract String UNAVAILABLE();
	/**
	 * Color of text next to unavailable option describing why it's unavailable
	 */
	public abstract String UNAVAILREASON();
	/**
	 * Used to give instructions
	 */
	public abstract String INSTRUCTION();
	/**
	 * Used for error messages through out the plugin
	 */
	public abstract String ERROR();
	/**
	 * Used for alert messages through out the plugin
	 */
	public abstract String ALERT();
	/**
	 * Used for all units mentioned in the HUD
	 */
	public abstract String UNIT();
	/**
	 * Used for the line of text below the HUD
	 */
	public abstract String ENDINSTRU();
}
