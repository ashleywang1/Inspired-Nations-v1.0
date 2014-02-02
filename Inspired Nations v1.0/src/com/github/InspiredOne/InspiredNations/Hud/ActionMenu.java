package com.github.InspiredOne.InspiredNations.Hud;


import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public abstract class ActionMenu extends Menu {

	private String current = "";
	private boolean registered = false;
	protected List<ActionManager<?>> managers = new ArrayList<ActionManager<?>>();
	
	public ActionMenu(PlayerData PDI) {
		super(PDI);
		managers.add(new TaxTimerManager<ActionMenu>(this));
	}

	public final void Update() {
		this.actionResponse();
		if (!current.equals(this.getPromptText())) {
			PDI.getCon().outputNextPrompt();
			current = this.getPromptText();
			for(ActionManager<?> mana:this.managers) {
				mana.textChange();
			}
		}
		else return;
	}
	
	public void register() {
		if(!registered) {
			for(ActionManager<?> manager:this.getActionManager()) {
				manager.stopListening();
			}
			for(ActionManager<?> manager:this.getActionManager()) {
				manager.startListening();
			}
			registered = true;
		}
	}
	public void unregister() {
		this.setError(MenuError.NO_ERROR());
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		registered = false;
	}
	
	@Override
	protected String getError() {
		String output = (String) this.getContext().getSessionData(ContextData.Error);
		return output;
	}
	
	
	/**
	 * Gets all the action managers for this menu.
	 * @return	an <code>ArrayList</code> of all the action managers
	 */
	public final List<ActionManager<?>> getActionManager() {
		return managers;
	}

	/**
	 * Called whenever the menu is updated.
	 */
	public abstract void actionResponse();

}
