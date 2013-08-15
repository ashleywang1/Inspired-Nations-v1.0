package com.github.InspiredOne.InspiredNations.Hud;


import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class ActionMenu extends Menu {

	private String current;
	
	public ActionMenu(PlayerData PDI) {
		super(PDI);
		current = this.getPromptText();
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
		MenuError output = (MenuError) this.getContext().getSessionData(ContextData.Error);
		switch(output) {
		case NO_ERROR:
			return output.toString();
		default:
			return "\n" + TextColor.ALERT + output.toString();
		}
	}
	
	@Override
	public final Menu getPreviousMenu() {
		this.setError(MenuError.NO_ERROR);
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return PreviousMenu();
	}
	
	public abstract Menu PreviousMenu();

	@Override
	public final Menu getNextMenu(String input) {
		this.setError(MenuError.NO_ERROR);
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return this.NextMenu(input);
	}
	public abstract Menu NextMenu(String input);
	

}
