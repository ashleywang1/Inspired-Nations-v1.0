package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public abstract class Option {

	private final String label;
	protected InspiredNations plugin;
	
	public Option(InspiredNations instance, String label) {
		this.label = label;
		this.plugin = instance;
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
	
}
