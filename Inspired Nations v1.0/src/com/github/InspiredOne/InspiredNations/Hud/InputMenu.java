package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.InputManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public abstract class InputMenu extends ActionMenu {

	protected List<ActionManager> managers = new ArrayList<ActionManager>();
	
	public InputMenu(PlayerData PDI) {
		super(PDI);
		managers.add(new InputManager(plugin, this, this.getTabOptions()));
	}
	
	@Override
	public final Menu NextMenu(String input) {
		MenuError error = this.validate(input);
		this.setError(error);
		switch(error) {
		case NO_ERROR:
			this.useInput(input);
			return this.nextMenu();
		default:
			return this.getSelf();
		}
	}
	
	@Override
	public List<ActionManager> getActionManager() {
		return managers;
	}
	
	public abstract Menu nextMenu();
	/**
	 * 
	 * @param input	the <code>String</code> that the player input
	 * @return		the <code>MenuError</code> that the input throws. Returns <code>MenuError.NO_ERROR</code> if no error
	 */
	public abstract MenuError validate(String input);
	/**
	 * Conditions and implements the input.
	 * @param input	the <code>String</code> that the player input
	 */
	public abstract void useInput(String input);
	
	public abstract List<String> getTabOptions();
}
