package com.github.InspiredOne.InspiredNations.Hud;


import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public abstract class ActionMenu extends Menu {

	private String current = "";
	protected List<ActionManager<?>> managers = new ArrayList<ActionManager<?>>();
	
	public ActionMenu(PlayerData PDI) {
		super(PDI);
	}

	public final void Update() {
		this.actionResponse();
		if (!current.equals(this.getPromptText())) {
			PDI.getCon().outputNextPrompt();
			current = this.getPromptText();
		}
		else return;
	}
	
	public void register() {
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.startListening();
		}
	}
	
	@Override
	protected String getError() {
		String output = (String) this.getContext().getSessionData(ContextData.Error);
		return output;
	}
	
	@Override
	public final Menu getPreviousMenu() {
		this.setError(MenuError.NO_ERROR());
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		return PreviousMenu();
	}
	
	@Override
	public final Menu getNextMenu(String input) {
		this.setError(MenuError.NO_ERROR());
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		return this.NextMenu(input);
	}
	
	/**
	 * Gets all the action managers for this menu.
	 * @return	an <code>ArrayList</code> of all the action managers
	 */
	public final List<ActionManager<?>> getActionManager() {
		return managers;
	}
	
	/**
	 * Used to return a prompt based on the input
	 * @param input	the player's input
	 * @return	the next <code>Menu</code>
	 */
	public abstract Menu NextMenu(String input);
	/**
	 * Called whenever the menu is updated.
	 */
	public abstract void actionResponse();

	public abstract Menu PreviousMenu();
}
