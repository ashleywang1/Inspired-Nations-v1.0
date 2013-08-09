package com.github.InspiredOne.InspiredNations.Hud;

import java.util.List;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public abstract class ActionMenu extends Menu {

	private String current;
	
	public ActionMenu(InspiredNations instance, PlayerData PDI) {
		super(instance, PDI);
		current = this.getPromptText();
	}

	public void Update() {
		if (current.equals(this.getPromptText())) {
			PDI.getCon().outputNextPrompt();
		}
		else return;
	}
	
	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PlayerData getPlayerData() {
		return this.PDI;
	}
	
	public abstract List<ActionManager> getActionManager(); 
	

}
