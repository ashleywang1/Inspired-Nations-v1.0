package com.github.InspiredOne.InspiredNations.Hud;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.PlayerData;

public abstract class PassByMenu extends OptionMenu {

	public PassByMenu(PlayerData PDI) {
		super(PDI);
	}
	
	@Override
	public boolean blocksForInput() {
		if (this.options.size() == 1) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public Prompt getNextPrompt(ConversationContext arg0) {
		return this.getNextMenu("1");
		
	}
	
	@Override
	public Menu getPreviousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
