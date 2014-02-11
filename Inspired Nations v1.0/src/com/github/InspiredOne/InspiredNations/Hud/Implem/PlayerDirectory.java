package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PlayerDirectory extends TabSelectOptionMenu<PlayerID> {

	public PlayerDirectory(PlayerData PDI) {
		super(PDI);
		System.out.println("In PlayerDirectory Constructor");
	}

	@Override
	public Menu getPreviousPrompt() {
		return new MainHud(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(PlayerID player:InspiredNations.playerdata.keySet()) {
			this.taboptions.add(player);
		}
		
		options.add(new PromptOption(this, "Profile", new PlayerProfile(PDI,this)));
	}

	@Override
	public String getHeader() {
		return "Player Directory";
	}

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new PlayerDirectory(PDI);
	}

}
