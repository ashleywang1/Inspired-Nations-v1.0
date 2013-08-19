package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.InputManager;

public abstract class InputMenu extends ActionMenu {

	protected List<ActionManager> managers = new ArrayList<ActionManager>();
	
	public InputMenu(PlayerData PDI) {
		super(PDI);
		managers.add(new InputManager(this.plugin, this, this.getTabOptions()));
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
	public List<ActionManager> getActionManager() {
		return managers;
	}
	
	@Override
	public Menu getPassTo() {
		return this.nextMenu();
	}
	
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
}
