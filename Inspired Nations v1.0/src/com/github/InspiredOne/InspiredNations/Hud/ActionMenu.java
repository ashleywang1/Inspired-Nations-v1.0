package com.github.InspiredOne.InspiredNations.Hud;


import java.util.List;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class ActionMenu extends Menu {

	private String current;
	
	public ActionMenu(InspiredNations instance, PlayerData PDI) {
		super(instance, PDI);
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
			manager.startListening();
		}
	}
	
	public abstract List<ActionManager> getActionManager();

	@Override
	public String getError() {
		MenuError output = (MenuError) PDI.getCon().getContext().getSessionData("Error");
		if(output == null) return "";
		else {
			return "\n" + TextColor.ALERT + output.toString();
		}
	}
	
	@Override
	public Prompt getPreviousPrompt() {
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return PreviousPrompt();
	}
	
	public abstract Prompt PreviousPrompt();

	@Override
	public Prompt getNextPrompt(String input) {
		for(ActionManager manager:this.getActionManager()) {
			manager.stopListening();
		}
		return this.NextPrompt();
	}
	public abstract Prompt NextPrompt();
	

}
