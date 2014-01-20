package com.github.InspiredOne.InspiredNations.Hud;

import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.InputManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class InputMenu extends ActionMenu {

	public InputMenu(PlayerData PDI) {
		super(PDI);

	}
	
	@Override
	public final Menu NextMenu(String input) {
		String error = this.validate(input);
		this.setError(error);
		if(error.isEmpty()) {
			this.useInput(input);
			return this.nextMenu();
		}
		else {
			return this.getSelf();
		}
	}
	
	@Override
	public Menu getPassTo() {
		return this.nextMenu();
	}
	
	@Override
	public String getFiller() {
		return TextColor.INSTRUCTION + this.getInstructions() + "\n";
	}
	
	@Override
	public void actionResponse() {
		
	}
	
	@Override
	public final void init() {
		this.Init();
		managers.add(new InputManager(this, this.getTabOptions()));
	}
	/**
	 * 
	 * @return	the menu to go to if passBy() returns true
	 */
	public abstract Menu nextMenu();
	/**
	 * 
	 * @param input	the <code>String</code> that the player input
	 * @return		the <code>MenuError</code> that the input throws. Returns <code>MenuError.NO_ERROR</code> if no error
	 */
	public abstract String validate(String input);
	/**
	 * Conditions and implements the input.
	 * @param input	the <code>String</code> that the player input
	 */
	public abstract void useInput(String input);
	
	public abstract List<String> getTabOptions();
	/**
	 * 
	 * @return	The text that instructs the player what to input
	 */
	public abstract String getInstructions();
	/**
	 * Used to do things for the conversation, but only for when the user is directed to it. Use
	 * for adding options, managers, and tab-completes.
	 */
	public abstract void Init();
}
