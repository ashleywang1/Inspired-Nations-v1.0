package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PlayerDirectory extends TabSelectOptionMenu {

	public PlayerDirectory(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return new MainHud(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void Init() {
		for(PlayerID player:InspiredNations.playerdata.keySet()) {
			this.taboptions.add(player);
		}
	}

	@Override
	public String getHeader() {
		return "Player Directory";
	}

}
