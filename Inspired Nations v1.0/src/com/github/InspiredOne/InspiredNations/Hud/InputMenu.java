package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.InputManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class InputMenu extends ActionMenu {

	public InputMenu(PlayerData PDI) {
		super(PDI);

	}
	
	@Override
	public final Menu getNextMenu(String input) {
		input = input.trim();
		String error = this.validate(input);
		this.setError(error);
		if(error.isEmpty()) {
			this.useInput(input);
			return this.nextMenu();
		}
		else {
			return getSelf();
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
	public void reset() {
		managers = new ArrayList<ActionManager<?>>();
		managers.add(new TaxTimerManager<ActionMenu>(this));
		managers.add(new InputManager<InputMenu>(this, this.getTabOptions()));
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
	 * Returns a new instance of itself. Used for user input errors.
	 * @return	the <code>Menu</code> of itself
	 */
	public abstract InputMenu getSelf();
}
