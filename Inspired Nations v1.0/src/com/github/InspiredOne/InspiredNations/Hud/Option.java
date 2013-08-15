package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public abstract class Option {

	private final String label;
	protected InspiredNations plugin;
	protected OptionMenu menu;
	private OptionUnavail reason;
	public Option(OptionMenu menu, String label, OptionUnavail reason) {
		this.menu = menu;
		this.label = label;
		this.plugin = this.menu.plugin;
		this.reason = reason;

	}
	/**
	 * Interprets the input and executes the associated task, then returns
	 * the next Menu for the conversation.
	 * 
	 * @param input	the String to be interpreted for determining the output
	 * @return		the Menu that the conversation should proceed to next
	 */
	public abstract Menu response(String input);
	
	/**
	 * Gets the label that is to be used within the menu.
	 * 
	 * @return	the String as it should appear on the menu
	 */
	public String getLabel() {
		return this.label;
	}
	
	public boolean isAvailable() {
		switch(reason) {
		case NOT_UNAVAILABLE:
			return true;
		default:
			return false;
		}
	}
	
	public String getUnvailReason() {
		return this.reason.toString();
	}
}
