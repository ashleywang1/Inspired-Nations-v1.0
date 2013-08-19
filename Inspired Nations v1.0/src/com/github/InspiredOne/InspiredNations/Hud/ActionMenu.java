package com.github.InspiredOne.InspiredNations.Hud;


import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public abstract class ActionMenu extends Menu {

	private String current = "";
	
	public ActionMenu(PlayerData PDI) {
		super(PDI);
	}

	public void Update() {
		if (!current.equals(this.getPromptText())) {
			PDI.getCon().outputNextPrompt();
			current = this.getPromptText();
		}
		else return;
	}
	
	public PlayerData getPlayerData() {
		return this.PDI;
	}
	
	public void register() {
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		for(ActionManager manager:this.getActionManager()) {
			manager.startListening();
		}
	}
	
	public abstract List<ActionManager> getActionManager();

	@Override
	protected String getError() {
		String output = (String) this.getContext().getSessionData(ContextData.Error);
		return output;
	}
	
	@Override
	public final Menu getPreviousMenu() {
		this.setError(MenuError.NO_ERROR());
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return PreviousMenu();
	}
	
	public abstract Menu PreviousMenu();

	@Override
	public final Menu getNextMenu(String input) {
		this.setError(MenuError.NO_ERROR());
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return this.NextMenu(input);
	}
	public abstract Menu NextMenu(String input);
	

}
