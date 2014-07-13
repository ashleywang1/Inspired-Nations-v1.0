package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;

public class ChatMenu extends TabSelectOptionMenu<Alert> {

	private Menu previous;
	
	public ChatMenu(PlayerData PDI, Menu previous) {
		super(PDI);
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return null;
	}

	@Override
	public void addTabOptions() {
		
	}

	@Override
	public void addOptions() {
		
	}

	@Override
	public void addActionManagers() {
		
	}

	@Override
	public String getHeader() {
		return "Chat";
	}

}
